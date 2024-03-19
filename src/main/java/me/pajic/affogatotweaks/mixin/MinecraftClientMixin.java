package me.pajic.affogatotweaks.mixin;

import me.pajic.affogatotweaks.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.server.SaveLoader;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Shadow @Final public GameOptions options;

    // Ensures that the mod config is loaded after every other mod initialization process has completed,
    // by loading it on world load. This is to ensure that all modded enchantments are picked up by the mod.
    @Inject(method = "startIntegratedServer", at = @At("HEAD"))
    private void preStart(String levelName, LevelStorage.Session session, ResourcePackManager dataPackManager, SaveLoader saveLoader, boolean newWorld, CallbackInfo ci) {
        new Config();
    }

    // Skips the front view third person camera mode
    @Inject(method = "handleInputEvents", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/Perspective;isFirstPerson()Z", ordinal = 0, shift = At.Shift.BEFORE))
    private void skipFrontViewCameraMode(CallbackInfo ci) {
        if (options.getPerspective().isFrontView()) {
            options.setPerspective(options.getPerspective().next());
        }
    }
}
