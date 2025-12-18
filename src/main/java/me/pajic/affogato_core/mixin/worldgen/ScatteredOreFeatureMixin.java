package me.pajic.affogato_core.mixin.worldgen;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogato_core.Main;
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
        return Math.max(original, Main.CONFIG.worldgen.ancientDebrisMinAmount.get());
    }
}
