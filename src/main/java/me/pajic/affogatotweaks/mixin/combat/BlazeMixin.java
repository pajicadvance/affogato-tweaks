package me.pajic.affogatotweaks.mixin.combat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.monster.Blaze;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Blaze.class)
public class BlazeMixin {

    @ModifyExpressionValue(
            method = "createAttributes",
            at = @At(
                    value = "CONSTANT",
                    args = "doubleValue=48.0"
            )
    )
    private static double modifyFollowRange(double original) {
        return 32;
    }
}
