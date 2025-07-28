package me.pajic.affogatotweaks.mixin.combat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogatotweaks.values.MobValues;
import net.minecraft.world.entity.monster.Ravager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Ravager.class)
public class RavagerMixin {

    @ModifyExpressionValue(
            method = "createAttributes",
            at = @At(
                    value = "CONSTANT",
                    args = "doubleValue=32.0"
            )
    )
    private static double modifyFollowRange(double original) {
        return MobValues.RAVAGER_FOLLOW_RANGE;
    }

    @ModifyExpressionValue(
            method = "strongKnockback",
            at = @At(
                    value = "CONSTANT",
                    args = "doubleValue=0.001"
            )
    )
    private double modifyRoarKnockbackMaxIntensity(double constant) {
        return 0.1;
    }
}
