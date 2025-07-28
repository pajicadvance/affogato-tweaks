package me.pajic.affogatotweaks.mixin.hunger;

import me.pajic.affogatotweaks.values.ExhaustionValues;
import net.minecraft.world.effect.HungerMobEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(HungerMobEffect.class)
public class HungerMobEffectMixin {

    @ModifyArg(
            method = "applyEffectTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V"
            )
    )
    private float hungerEffectTickExhaustion(float original) {
        return ExhaustionValues.HUNGER_EFFECT_TICK;
    }
}
