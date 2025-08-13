package me.pajic.affogatotweaks.mixin.shaderlock;

import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import me.pajic.affogatotweaks.shaderlock.ShaderLock;
import net.irisshaders.iris.Iris;
import net.irisshaders.iris.config.IrisConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@IfModLoaded("iris")
@Mixin(value = IrisConfig.class, remap = false)
public abstract class IrisConfigMixin {

    @Shadow
    private String shaderPackName;

    @Shadow
    public abstract void save() throws IOException;

    @Shadow
    private boolean enableShaders;

    @Inject(
            method = "load",
            at = @At("TAIL")
    )
    private void clearShaderIfNotAllowed(CallbackInfo ci) {
        try {
            if (!ShaderLock.isShaderAllowed(shaderPackName)) {
                enableShaders = false;
                shaderPackName = null;
                try {
                    save();
                } catch (IOException e) {
                    Iris.logger.error("Exception while saving config file" + e);
                }
            }
        } catch (IOException e) {
            ShaderLock.LOGGER.error("Unable to check if shader is allowed", e);
        }
    }
}
