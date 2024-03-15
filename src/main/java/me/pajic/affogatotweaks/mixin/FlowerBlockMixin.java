package me.pajic.affogatotweaks.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FlowerBlock.class)
public class FlowerBlockMixin {

    @Mutable @Shadow @Final
    private StatusEffect effectInStew;

    // Swaps the Night Vision status effect in suspicious stews with Invisibility
    @Inject(method = "<init>", at = @At("RETURN"))
    private void swapNightVisionEffect(StatusEffect suspiciousStewEffect, int effectDuration, AbstractBlock.Settings settings, CallbackInfo ci) {
        if (effectInStew == StatusEffects.NIGHT_VISION) {
            effectInStew = StatusEffects.INVISIBILITY;
        }
    }
}
