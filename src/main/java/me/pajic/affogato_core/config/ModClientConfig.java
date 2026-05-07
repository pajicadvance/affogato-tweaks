package me.pajic.affogato_core.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class ModClientConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().excludeFieldsWithModifiers(Modifier.PRIVATE).create();
    public int shaderPreset = -1;
    public Map<String, Integer> perDimensionBrightness = new HashMap<>();
    public int raiseHotbarPixels = 0;
    public boolean disableLBGLayers = false;
    private File file;

    public static ModClientConfig load(File file) {
        ModClientConfig config;

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                config = GSON.fromJson(reader, ModClientConfig.class);
            } catch (Exception e) {
                config = new ModClientConfig();
            }
        } else {
            config = new ModClientConfig();
        }

        config.file = file;
        config.writeChanges();

        return config;
    }

    public int getOrCreateDimensionBrightness(@Nullable Identifier id) {
        if (id != null) {
            String idString = id.toString();
            if (perDimensionBrightness.containsKey(idString)) return perDimensionBrightness.get(idString);
            perDimensionBrightness.put(idString, -1);
            writeChanges();
            return -1;
        }
        return -2;
    }

    public void saveDimensionBrightness(@Nullable Identifier id, int brightness) {
        if (id != null) {
            perDimensionBrightness.put(id.toString(), brightness);
            writeChanges();
        }
    }

    public void writeChanges() {
        File dir = file.getParentFile();

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new RuntimeException("Could not create parent directories");
            }
        } else if (!dir.isDirectory()) {
            throw new RuntimeException("The parent file is not a directory");
        }

        try (FileWriter writer = new FileWriter(file)) {
            GSON.toJson(this, writer);
        } catch (IOException e) {
            throw new RuntimeException("Could not save configuration file", e);
        }
    }
}
