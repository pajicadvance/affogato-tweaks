package me.pajic.affogatotweaks.mixin.combat;

import me.pajic.affogatotweaks.Main;
import me.pajic.affogatotweaks.values.MobValues;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity {
    protected MobMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Unique private int buffLevel = 0;

    @Inject(
            method = "finalizeSpawn",
            at = @At("TAIL")
    )
    private void applyEffects(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        if (!MobSpawnType.isSpawner(spawnType)) {
            MobValues.MOB_EFFECTS.getOrDefault(getType(), Set.of()).forEach(mobEffect -> {
                if (level.getRandom().nextFloat() < Mth.lerp(difficulty.getSpecialMultiplier(), 0.085F, 0.25F)) {
                    Mob mob = (Mob) (Object) this;
                    Holder<DimensionType> dimension = level.getLevel().dimensionTypeRegistration();
                    float mult = (dimension.is(BuiltinDimensionTypes.OVERWORLD) && mob.getY() < 0) || dimension.is(BuiltinDimensionTypes.NETHER) ? 0.15F : 0.1F;
                    if (level.getRandom().nextFloat() < mult * difficulty.getEffectiveDifficulty()) {
                        int maxAmplifier = MobValues.MAX_EFFECT_AMPLIFIERS.getOrDefault(mobEffect, 0);
                        int amplifier = Mth.clamp(Math.round(difficulty.getSpecialMultiplier() * level.getRandom().nextFloat() * maxAmplifier * maxAmplifier), 0, maxAmplifier);
                        addEffect(new MobEffectInstance(mobEffect, -1, amplifier));
                        buffLevel += 1 + amplifier;
                    }
                }
            });
        }
        if (getMaxHealth() > 20.0F) heal(getMaxHealth());
        if (Main.DEBUG && buffLevel > 0) {
            System.out.println("buff level " + buffLevel + " " + getName().getString() + " at " + getX() + " " + getY() + " " + getZ());
        }
    }

    @Inject(
            method = "dropCustomDeathLoot",
            at = @At("TAIL")
    )
    private void dropRewardsIfBuffed(ServerLevel level, DamageSource damageSource, boolean recentlyHit, CallbackInfo ci) {
        if (lastHurtByPlayerTime > 0 && level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            int maxBuffLevel = 0;
            for (Holder<MobEffect> mobEffect : MobValues.MOB_EFFECTS.getOrDefault(getType(), Set.of())) {
                maxBuffLevel += 1 + MobValues.MAX_EFFECT_AMPLIFIERS.getOrDefault(mobEffect, 0);
            }
            Mob mob = (Mob) (Object) this;
            if (mob instanceof Zombie zombie && zombie.getEntityData().get(Main.IS_LEADER)) buffLevel += 6;
            mob.getArmorSlots().forEach(armorSlot -> {if (!armorSlot.isEmpty()) buffLevel++;});
            int buffLevelThreshold = Math.round(maxBuffLevel * (2F / 3));
            if (Main.DEBUG) System.out.println("max " + maxBuffLevel + " threshold " + buffLevelThreshold + " current " + buffLevel);
            if (buffLevel > buffLevelThreshold) {
                List<Holder<Enchantment>> enchantments = new ArrayList<>();
                level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentTags.ON_RANDOM_LOOT).forEach(enchantments::add);
                Util.getRandomSafe(enchantments, level.random).ifPresent(enchantment -> {
                    int i = Mth.nextInt(random, enchantment.value().getMinLevel(), Math.min(buffLevel - buffLevelThreshold + 2, enchantment.value().getMaxLevel()));
                    ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
                    book.enchant(enchantment, i);
                    spawnAtLocation(book);
                });
            }
            if (buffLevel > 0) {
                level.addFreshEntity(new ExperienceOrb(level, getX(), getY(), getZ(), buffLevel * 3));
            }
        }
    }

    @Inject(
            method = "addAdditionalSaveData",
            at = @At("TAIL")
    )
    private void saveMobBuffLevel(CompoundTag compound, CallbackInfo ci) {
        compound.putInt("BuffLevel", buffLevel);
    }

    @Inject(
            method = "readAdditionalSaveData",
            at = @At("TAIL")
    )
    private void loadMobBuffLevel(CompoundTag compound, CallbackInfo ci) {
        buffLevel = compound.getInt("BuffLevel");
    }
}
