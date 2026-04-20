package me.pajic.affogato_core.mixin.gameplay;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogato_core.ClientMain;
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
        int g = ClientMain.CONFIG.perDimensionBrightness.getOrDefault(level.dimension().identifier(), -1);
        return g == -1 ? original : (float) g / 100;
    }
}
