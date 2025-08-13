package me.pajic.affogatotweaks.shaderlock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import me.pajic.affogatotweaks.Main;
import net.fabricmc.loader.api.FabricLoader;
import net.irisshaders.iris.Iris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.stream.Stream;

public class ShaderLock {
    public static final Logger LOGGER = LoggerFactory.getLogger("ShaderLock");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE_PATH = FabricLoader.getInstance().getConfigDir().resolve("shaderlock");
    public static boolean setupMode = false;
    public static final Map<String, Map<String, String>> SHADERS = new Object2ObjectArrayMap<>();

    public static void init() throws IOException {
        try (Stream<Path> fileStream = Files.walk(Iris.getShaderpacksDirectory(), 1)) {
            fileStream.forEach(shaderPackPath -> {
                File shaderPack = shaderPackPath.toFile();
                String shaderPackName = shaderPack.getName();
                if (shaderPack.isDirectory() && !shaderPackName.equals("shaderpacks")) {
                    Map<String, String> fileHashes = new Object2ObjectArrayMap<>();
                    try (Stream<Path> fileStream1 = Files.walk(shaderPackPath)) {
                        fileStream1.forEach(path -> {
                            try {
                                String fileName = path.toString().substring(path.toString().lastIndexOf(File.separator) + 1);
                                File f = path.toFile();
                                if (!fileName.startsWith(".") && !f.isDirectory()) {
                                    fileHashes.put(shaderPackPath.relativize(path).toString(), SHA256.calculateSHA256(path.toFile()));
                                }
                            } catch (IOException | NoSuchAlgorithmException e) {
                                LOGGER.error("Failed to process shader file", e);
                            }
                        });
                    } catch (IOException e) {
                        LOGGER.error("Failed to process shader directory", e);
                    }
                    SHADERS.put(shaderPackName, fileHashes);
                } else if (shaderPack.isFile() && shaderPackName.endsWith(".zip")) {
                    try {
                        SHADERS.put(shaderPackName, Map.of(shaderPackName, SHA256.calculateSHA256(shaderPack)));
                    } catch (IOException | NoSuchAlgorithmException e) {
                        LOGGER.error("Failed to process shader archive", e);
                    }
                }
            });
        }
    }

    public static boolean isShaderAllowed(String name) throws IOException {
        InputStream is = Main.class.getResourceAsStream("/keys/" + name + "_sha256");
        if (setupMode)
            try (FileWriter fw = new FileWriter(Iris.getShaderpacksDirectory() + File.separator + name + "_sha256")) {
                GSON.toJson(SHADERS.get(name), fw);
                fw.flush();
            }
        if (is != null) {
            return SHADERS.get(name).equals(GSON.fromJson(new InputStreamReader(is), new TypeToken<Map<String, String>>() {}.getType()));
        }
        return false;
    }

    public static void read() {
        try (FileReader reader = new FileReader(FILE_PATH.toFile())) {
            setupMode = GSON.fromJson(reader, boolean.class);
            if (setupMode) LOGGER.info("Setup mode enabled");
        } catch (FileNotFoundException | JsonSyntaxException e) {
            if (e instanceof JsonSyntaxException) LOGGER.info("Incorrect syntax: {}", e.getMessage());
            else LOGGER.info("File not found");
        } catch (IOException e) {
            LOGGER.error("Failed to read file", e);
        }
    }
}
