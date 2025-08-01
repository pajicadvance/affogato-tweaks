package me.pajic.affogatotweaks.mixin.gameplay;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(Zombie.class)
public abstract class ZombieMixin extends Monster {
    protected ZombieMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow @Final private static ResourceLocation ZOMBIE_RANDOM_SPAWN_BONUS_ID;

    @WrapMethod(method = "dropCustomDeathLoot")
    private void dropEnchantedBookIfLeader(ServerLevel level, DamageSource damageSource, boolean recentlyHit, Operation<Void> original) {
        original.call(level, damageSource, recentlyHit);
        if (!((Zombie) (Object) this instanceof ZombifiedPiglin) && getMaxHealth() > 20.0) {
            List<Holder<Enchantment>> enchantments = new ArrayList<>();
            level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentTags.ON_RANDOM_LOOT).forEach(enchantments::add);
            Optional<Holder<Enchantment>> optional = Util.getRandomSafe(enchantments, level.random);
            if (optional.isPresent()) {
                int i = Mth.nextInt(random, optional.get().value().getMinLevel(), optional.get().value().getMaxLevel());
                ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
                book.enchant(optional.get(), i);
                spawnAtLocation(book);
            }
        }
    }

    @WrapWithCondition(
            method = "finalizeSpawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/Zombie;handleAttributes(F)V"
            )
    )
    private boolean noLeaderIfSpawnedFromSpawnerBlock(Zombie instance, float difficulty, @Local(argsOnly = true) MobSpawnType spawnType) {
        if (spawnType == MobSpawnType.SPAWNER) {
            getAttribute(Attributes.KNOCKBACK_RESISTANCE).addOrReplacePermanentModifier(new AttributeModifier(RANDOM_SPAWN_BONUS_ID, random.nextDouble() * 0.05F, AttributeModifier.Operation.ADD_VALUE));
            double d = random.nextDouble() * 1.5 * difficulty;
            if (d > 1.0) getAttribute(Attributes.FOLLOW_RANGE).addOrReplacePermanentModifier(new AttributeModifier(ZOMBIE_RANDOM_SPAWN_BONUS_ID, d, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            return false;
        }
        return true;
    }
}
