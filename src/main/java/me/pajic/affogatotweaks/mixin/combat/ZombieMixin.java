package me.pajic.affogatotweaks.mixin.combat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogatotweaks.Main;
import me.pajic.affogatotweaks.values.MobValues;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Zombie.class)
public abstract class ZombieMixin extends Mob{
    protected ZombieMixin(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow @Final private static ResourceLocation ZOMBIE_RANDOM_SPAWN_BONUS_ID;
    @Unique private int soundTimer = 0;

    @ModifyExpressionValue(
            method = "createAttributes",
            at = @At(
                    value = "CONSTANT",
                    args = "doubleValue=35.0"
            )
    )
    private static double modifyFollowRange(double original) {
        return MobValues.ZOMBIE_FOLLOW_RANGE;
    }

    @ModifyExpressionValue(
            method = "hurt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"
            )
    )
    private boolean onlyCaptainSpawnsReinforcements(boolean original) {
        return entityData.get(Main.IS_LEADER) && original;
    }

    @Inject(
            method = "hurt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)V"
            )
    )
    private void playSoundOnReinforcementsSpawn(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        soundTimer = 40;
    }

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/Zombie;isUnderWaterConverting()Z"
            )
    )
    private void tickReinforcementSound(CallbackInfo ci) {
        if (soundTimer > 0) {
            if (soundTimer % 10 == 0) playSound(getBlockStateOn().getSoundType().getBreakSound());
            soundTimer--;
        }
    }

    @Inject(
            method = "handleAttributes",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/Zombie;setCanBreakDoors(Z)V"
            )
    )
    private void markLeader(float difficulty, CallbackInfo ci) {
        heal(getMaxHealth()); // fix bug reported in 2021 lol
        entityData.set(Main.IS_LEADER, true);
    }

    @WrapWithCondition(
            method = "finalizeSpawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/Zombie;handleAttributes(F)V"
            )
    )
    private boolean noLeaderIfSpawnedFromSpawnerBlock(Zombie instance, float difficulty, @Local(argsOnly = true) MobSpawnType spawnType) {
        if (MobSpawnType.isSpawner(spawnType)) {
            getAttribute(Attributes.KNOCKBACK_RESISTANCE).addOrReplacePermanentModifier(new AttributeModifier(RANDOM_SPAWN_BONUS_ID, random.nextDouble() * 0.05F, AttributeModifier.Operation.ADD_VALUE));
            double d = random.nextDouble() * 1.5 * difficulty;
            if (d > 1.0) getAttribute(Attributes.FOLLOW_RANGE).addOrReplacePermanentModifier(new AttributeModifier(ZOMBIE_RANDOM_SPAWN_BONUS_ID, d, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            return false;
        }
        return true;
    }

    @Inject(
            method = "defineSynchedData",
            at = @At("TAIL")
    )
    private void defineLeaderData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(Main.IS_LEADER, false);
    }

    @Inject(
            method = "addAdditionalSaveData",
            at = @At("TAIL")
    )
    private void saveLeaderData(CompoundTag compound, CallbackInfo ci) {
        compound.putBoolean("IsLeader", entityData.get(Main.IS_LEADER));
    }

    @Inject(
            method = "readAdditionalSaveData",
            at = @At("TAIL")
    )
    private void readLeaderData(CompoundTag compound, CallbackInfo ci) {
        entityData.set(Main.IS_LEADER, compound.getBoolean("IsLeader"));
    }
}
