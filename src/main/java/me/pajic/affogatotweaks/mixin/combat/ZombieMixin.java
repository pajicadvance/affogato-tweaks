package me.pajic.affogatotweaks.mixin.combat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogatotweaks.values.MobValues;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Zombie.class)
public abstract class ZombieMixin extends Monster {
    protected ZombieMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

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
        return getMaxHealth() > 20.0 && original;
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
    private void fixBugReportedIn2021(float difficulty, CallbackInfo ci) {
        heal(getMaxHealth());
    }
}
