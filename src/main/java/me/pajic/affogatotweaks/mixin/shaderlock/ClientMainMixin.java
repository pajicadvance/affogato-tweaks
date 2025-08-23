package me.pajic.affogatotweaks.mixin.shaderlock;

import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import me.pajic.affogatotweaks.shaderlock.ShaderLock;
import net.minecraft.client.main.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@IfModLoaded("iris")
@Mixin(Main.class)
public class ClientMainMixin {
    @Inject(
            method = "<clinit>",
            at = @At("HEAD")
    )
    private static void onInit(CallbackInfo ci) {
        ShaderLock.read();
    }
}