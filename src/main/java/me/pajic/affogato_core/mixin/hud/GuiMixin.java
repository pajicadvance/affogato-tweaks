package me.pajic.affogato_core.mixin.hud;

import me.pajic.affogato_core.config.ModClientConfigHolder;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(
            method = "extractHotbarAndDecorations",
            at = @At("HEAD")
    )
    private void raiseHotbarStart(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        int raisePixels = ModClientConfigHolder.options().raiseHotbarPixels;
        if (raisePixels > 0) {
            graphics.pose().pushMatrix();
            graphics.pose().translate(0, -raisePixels);
        }
    }

    @Inject(
            method = "extractHotbarAndDecorations",
            at = @At("TAIL")
    )
    private void raiseHotbarEnd(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (ModClientConfigHolder.options().raiseHotbarPixels > 0) {
            graphics.pose().popMatrix();
        }
    }

    @ModifyArg(
            method = "extractItemHotbar",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V",
                    ordinal = 1
            ),
            index = 5
    )
    private int fixHotbarSelector(int height) {
        return ModClientConfigHolder.options().raiseHotbarPixels > 0 ? height + 1 : height;
    }
}
