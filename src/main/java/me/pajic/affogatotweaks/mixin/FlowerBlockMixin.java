package me.pajic.affogatotweaks.mixin;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
    private MobEffect suspiciousStewEffect;

    // Swaps the Night Vision status effect in suspicious stews with Invisibility
    @Inject(method = "<init>", at = @At("RETURN"))
    private void swapNightVisionEffect(MobEffect suspiciousStewEffect, int effectDuration, BlockBehaviour.Properties settings, CallbackInfo ci) {
        if (this.suspiciousStewEffect == MobEffects.NIGHT_VISION) {
            this.suspiciousStewEffect = MobEffects.INVISIBILITY;
        }
    }
}
