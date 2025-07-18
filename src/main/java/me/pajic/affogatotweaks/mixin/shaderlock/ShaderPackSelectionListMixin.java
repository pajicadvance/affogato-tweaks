package me.pajic.affogatotweaks.mixin.shaderlock;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import me.pajic.affogatotweaks.shaderlock.ShaderLock;
import net.irisshaders.iris.gui.element.ShaderPackSelectionList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@IfModLoaded("iris")
@Mixin(value = ShaderPackSelectionList.class, remap = false)
public class ShaderPackSelectionListMixin {

    @Unique
    private static final Logger LOGGER = LoggerFactory.getLogger("ShaderLock");

    @WrapMethod(method = "addPackEntry")
    private void filter(int index, String name, Operation<Void> original) {
        try {
            if (ShaderLock.shaderAllowed(name)) original.call(index, name);
        } catch (IOException | NoSuchAlgorithmException e) {
            LOGGER.error("Unable to check if shader is allowed", e);
        }
    }
}