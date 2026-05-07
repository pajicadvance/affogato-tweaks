package me.pajic.affogato_core.iris;

import me.pajic.affogato_core.ClientMain;
import net.irisshaders.iris.Iris;

import java.io.IOException;
import java.util.Optional;

public class IrisHelper {

    public static void applyPreset(int preset) {
        if (preset == -1) Iris.getIrisConfig().setShadersEnabled(false);
        else {
            Iris.getIrisConfig().setShaderPackName("Affogato_" + ShaderPresetSetup.presets[preset] + ".zip");
            Iris.getIrisConfig().setShadersEnabled(true);
        }
        try {
            Iris.getIrisConfig().save();
            Iris.reload();
        } catch (IOException e) {
            ClientMain.LOGGER.error("Error while reloading Shaders for Iris!", e);
        }
    }

    public static int updatePreset(int preset) {
        if (preset != -1) {
            Optional<String> opt = Iris.getIrisConfig().getShaderPackName();
            if (opt.isPresent() && !opt.get().startsWith("Affogato_")) return -1;
            return preset;
        }
        return -1;
    }
}
