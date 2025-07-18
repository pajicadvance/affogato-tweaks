package me.pajic.affogatotweaks.mixin.nightvision;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.storage.loot.functions.SetStewEffectFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SetStewEffectFunction.class)
public class SetStewEffectFunctionMixin {

    @ModifyExpressionValue(
            method = "run",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/storage/loot/functions/SetStewEffectFunction$EffectEntry;effect()Lnet/minecraft/core/Holder;"
            )
    )
    private Holder<MobEffect> swapNightVisionForInvisibility(Holder<MobEffect> original) {
        if (original == MobEffects.NIGHT_VISION) {
            return MobEffects.INVISIBILITY;
        }
        return original;
    }
}
