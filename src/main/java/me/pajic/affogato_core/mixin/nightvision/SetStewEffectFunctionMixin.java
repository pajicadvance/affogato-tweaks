package me.pajic.affogato_core.mixin.nightvision;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogato_core.Main;
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
        if (Main.CONFIG.features.nightVisionNuke.get() && original == MobEffects.NIGHT_VISION) {
            return MobEffects.INVISIBILITY;
        }
        return original;
    }
}
