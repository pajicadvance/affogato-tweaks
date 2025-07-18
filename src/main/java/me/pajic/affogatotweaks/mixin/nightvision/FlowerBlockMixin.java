package me.pajic.affogatotweaks.mixin.nightvision;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.block.FlowerBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FlowerBlock.class)
public class FlowerBlockMixin {

    @ModifyExpressionValue(
            method = "makeEffectList",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/core/Holder;I)Lnet/minecraft/world/item/component/SuspiciousStewEffects$Entry;"
            )
    )
    private static SuspiciousStewEffects.Entry swapNightVisionForInvisibility(SuspiciousStewEffects.Entry original, @Local(argsOnly = true) float seconds) {
        if (original.effect() == MobEffects.NIGHT_VISION) {
            return new SuspiciousStewEffects.Entry(MobEffects.INVISIBILITY, Mth.floor(seconds * 20.0F));
        }
        return original;
    }
}