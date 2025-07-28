package me.pajic.affogatotweaks.mixin.worldgen;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogatotweaks.values.WorldgenValues;
import net.minecraft.world.level.levelgen.feature.ScatteredOreFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ScatteredOreFeature.class)
public class ScatteredOreFeatureMixin {

    @ModifyExpressionValue(
            method = "place",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/RandomSource;nextInt(I)I"
            )
    )
    private int setMinimumAncientDebrisAmount(int original) {
        return Math.max(original, WorldgenValues.ANCIENT_DEBRIS_MIN_AMOUNT);
    }
}
