package me.pajic.affogatotweaks.mixin;

import me.pajic.affogatotweaks.config.Config;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    // Ensures that the mod config is loaded after every other mod initialization process has completed,
    // by loading it on world load. This is to ensure that all modded enchantments are picked up by the mod
    @Inject(method = "loadWorld", at = @At("HEAD"))
    private void preLoad(CallbackInfo ci) {
        new Config();
    }
}