package me.pajic.affogatotweaks.mixin.combat;

import me.pajic.affogatotweaks.Main;
import me.pajic.affogatotweaks.values.MobValues;
import me.pajic.simple_smithing_overhaul.util.ModUtil;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
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
                float mult = difficulty.getEffectiveDifficulty();
                if (level.getRandom().nextFloat() < 0.08F * mult) {
                    int amplifier = Mth.clamp(Math.round(mult / level.getRandom().nextFloat()), 0, MobValues.MAX_EFFECT_AMPLIFIERS.getOrDefault(mobEffect, 0));
                    addEffect(new MobEffectInstance(mobEffect, -1, amplifier));
                    buffLevel += 1 + amplifier;
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
        Mob mob = (Mob) (Object) this;
        if (mob instanceof Zombie zombie && zombie.getEntityData().get(Main.IS_LEADER)) {
            if (Main.DEBUG) System.out.println("Applied leader bonus");
            buffLevel += 3;
        }
        if (buffLevel > 9) {
            List<Holder<Enchantment>> enchantments = new ArrayList<>();
            level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentTags.ON_RANDOM_LOOT).forEach(enchantments::add);
            Util.getRandomSafe(enchantments, level.random).ifPresent(enchantment -> {
                int i = ModUtil.calculateNewEnchantmentLevel(
                        Mth.clamp(buffLevel - 9, enchantment.value().getMinLevel(), enchantment.value().getMaxLevel()),
                        level.random, 1
                );
                ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
                book.enchant(enchantment, i);
                spawnAtLocation(book);
            });
        }
        if (buffLevel > 0) {
            ItemStack bottles = new ItemStack(Items.EXPERIENCE_BOTTLE);
            bottles.setCount(Mth.clamp(Mth.ceil((float) buffLevel / 3), 1, 3));
            spawnAtLocation(bottles);
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
