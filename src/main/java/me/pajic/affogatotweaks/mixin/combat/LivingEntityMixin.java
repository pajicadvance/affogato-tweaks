package me.pajic.affogatotweaks.mixin.combat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @ModifyExpressionValue(
            method = "getVisibilityPercent",
            at = @At(
                    value = "CONSTANT",
                    args = "doubleValue=0.8"
            )
    )
    private double modifyVisibilityWhenDiscrete(double original) {
        return 0.375;
    }

    @ModifyArg(
            method = "hurt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;hurtCurrentlyUsedShield(F)V"
            )
    )
    private float reduceProjectileShieldDamage(float damageAmount, @Local(argsOnly = true) DamageSource damageSource) {
        return damageSource.is(DamageTypeTags.IS_PROJECTILE) ? damageAmount / 3 : damageAmount;
    }
}
