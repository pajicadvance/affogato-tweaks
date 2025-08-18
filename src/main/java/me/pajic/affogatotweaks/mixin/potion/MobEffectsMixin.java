package me.pajic.affogatotweaks.mixin.potion;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MobEffects.class)
public class MobEffectsMixin {

    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=2445989"
            )
    )
    private static int changeAbsorptionColor(int original) {
        return 0xFEE342;
    }
}
