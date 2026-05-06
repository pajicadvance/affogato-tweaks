package me.pajic.affogato_core.mixin.gameplay;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogato_core.config.ModClientConfigHolder;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LightmapRenderStateExtractor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LightmapRenderStateExtractor.class)
public class LightmapRenderStateExtractorMixin {

    @ModifyExpressionValue(
            method = "extract",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Double;floatValue()F",
                    ordinal = 0
            )
    )
    private float modifyBrightness(float original, @Local(name = "level") ClientLevel level) {
        int g = ModClientConfigHolder.options().getOrCreateDimensionBrightness(level.dimension().identifier());
        return g < 0 ? original : (float) g / 100;
    }
}
