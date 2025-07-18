package me.pajic.affogatotweaks.mixin.combat;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalDoubleRef;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(
            method = "getVisibilityPercent",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;isDiscrete()Z"
            )
    )
    private void modifyVisibility(Entity lookingEntity, CallbackInfoReturnable<Double> cir, @Local LocalDoubleRef d) {
        d.set((d.get() / 0.8) * 0.375);
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
