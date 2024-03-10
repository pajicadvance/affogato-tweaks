package me.pajic.affogatotweaks.mixin.reacharound;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = InGameHud.class, priority = 1500)
public class InGameHudMixinSquared {

    @Shadow private int scaledHeight;

    @Shadow private int scaledWidth;

    @TargetHandler(
            mixin = "com.spanser.reacharound.mixin.client.InGameHudMixin",
            name = "renderPlacementAssistText"
    )
    @Redirect(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V"
            )
    )
    private void centerReacharoundWidget(MatrixStack instance, float x, float y, float z) {
        instance.translate(scaledWidth / 2f + 0.5, scaledHeight / 2f - 3.5, 0);
    }
}
