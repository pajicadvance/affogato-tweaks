package me.pajic.affogatotweaks.mixin.presencefootsteps;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DebugScreenOverlay.class, priority = 1500)
public class DebugScreenOverlayMixinSquared {

    // Disables the debug HUD info from Presence Footsteps
    @TargetHandler(mixin = "eu.ha3.presencefootsteps.mixins.MDebugHud", name = "onGetRightText")
    @Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true)
    private void disableDebugInfo(CallbackInfo ci) {
        ci.cancel();
    }
}
