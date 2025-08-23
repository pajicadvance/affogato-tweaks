package me.pajic.affogatotweaks.shaderlock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectObjectImmutablePair;
import me.pajic.affogatotweaks.Main;
import net.fabricmc.loader.api.FabricLoader;
import net.irisshaders.iris.Iris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ShaderLock {
    public static final Logger LOGGER = LoggerFactory.getLogger("ShaderLock");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE_PATH = FabricLoader.getInstance().getConfigDir().resolve("shaderlock");
    public static boolean setupMode = false;
    public static final Map<String, List<ObjectObjectImmutablePair<String, String>>> SHADERS = new Object2ObjectArrayMap<>();

    public static void init() throws IOException {
        try (Stream<Path> fileStream = Files.walk(Iris.getShaderpacksDirectory(), 1)) {
            fileStream.forEach(shaderPackPath -> {
                File shaderPack = shaderPackPath.toFile();
                String shaderPackName = shaderPack.getName();
                if (shaderPack.isDirectory() && !shaderPackName.equals("shaderpacks")) {
                    List<ObjectObjectImmutablePair<String, String>> fileHashes = new ArrayList<>();
                    try (Stream<Path> fileStream1 = Files.walk(shaderPackPath)) {
                        fileStream1.forEach(path -> {
                            try {
                                File file = path.toFile();
                                String fileName = file.getName();
                                if (!fileName.startsWith(".") && !file.isDirectory()) {
                                    fileHashes.add(new ObjectObjectImmutablePair<>(SHA256.calculateSHA256(path.toFile()), fileName));
                                }
                            } catch (IOException | NoSuchAlgorithmException e) {
                                LOGGER.error("Failed to process shader file", e);
                            }
                        });
                    } catch (IOException e) {
                        LOGGER.error("Failed to process shader directory", e);
                    }
                    fileHashes.sort(Comparator.comparing(ObjectObjectImmutablePair::right));
                    SHADERS.put(shaderPackName, fileHashes);
                } else if (shaderPack.isFile() && shaderPackName.endsWith(".zip")) {
                    try {
                        SHADERS.put(shaderPackName, List.of(new ObjectObjectImmutablePair<>(SHA256.calculateSHA256(shaderPack), shaderPackName)));
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
        if (is != null && SHADERS.containsKey(name)) {
            return SHADERS.get(name).equals(GSON.fromJson(new InputStreamReader(is), new TypeToken<List<ObjectObjectImmutablePair<String, String>>>() {}.getType()));
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
