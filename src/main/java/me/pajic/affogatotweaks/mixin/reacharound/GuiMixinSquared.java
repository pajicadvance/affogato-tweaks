package me.pajic.affogatotweaks.mixin.reacharound;

import com.bawnorton.mixinsquared.TargetHandler;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Gui.class, priority = 1500)
public class GuiMixinSquared {

    // Makes the indicator widgets from Reacharound align with the crosshair change from Centered Crosshair
    @Shadow private int screenHeight;

    @Shadow public int screenWidth;

    @TargetHandler(mixin = "com.spanser.reacharound.mixin.client.InGameHudMixin", name = "renderPlacementAssistText")
    @Redirect(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V"))
    private void centerReacharoundWidget(PoseStack instance, float x, float y, float z) {
        instance.translate(screenWidth / 2f + 0.5, screenHeight / 2f - 3.5, 0);
    }
}
