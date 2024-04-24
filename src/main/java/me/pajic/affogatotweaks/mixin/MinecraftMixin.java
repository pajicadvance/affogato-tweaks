package me.pajic.affogatotweaks.mixin;

import me.pajic.affogatotweaks.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.server.WorldStem;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow @Final public Options options;

    // Ensures that the mod config is loaded after every other mod initialization process has completed,
    // by loading it on world load. This is to ensure that all modded enchantments are picked up by the mod.
    @Inject(method = "doWorldLoad", at = @At("HEAD"))
    private void preStart(String levelName, LevelStorageSource.LevelStorageAccess session, PackRepository dataPackManager, WorldStem saveLoader, boolean newWorld, CallbackInfo ci) {
        new Config();
    }

    // Skips the front view third person camera mode
    @Inject(method = "handleKeybinds", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/CameraType;isFirstPerson()Z", ordinal = 0, shift = At.Shift.BEFORE))
    private void skipFrontViewCameraMode(CallbackInfo ci) {
        if (options.getCameraType().isMirrored()) {
            options.setCameraType(options.getCameraType().cycle());
        }
    }
}
