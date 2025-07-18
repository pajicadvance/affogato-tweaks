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
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.stream.Stream;

public class ShaderLock {
    private static final Logger LOGGER = LoggerFactory.getLogger("ShaderLock");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path filePath = FabricLoader.getInstance().getConfigDir().resolve("shaderlock");
    public static boolean setupMode = false;

    public static boolean shaderAllowed(String name) throws IOException, NoSuchAlgorithmException {
        Path shaderPackPath = Paths.get(Iris.getShaderpacksDirectory() + File.separator + name);
        File shaderPack = shaderPackPath.toFile();
        if (shaderPack.isDirectory()) {
            Map<String, String> fileHashes = new Object2ObjectArrayMap<>();
            try (Stream<Path> fileStream = Files.walk(shaderPackPath)) {
                fileStream.forEach(path -> {
                    try {
                        String fileName = path.toString().substring(path.toString().lastIndexOf(File.separator) + 1);
                        File f = path.toFile();
                        if (!fileName.startsWith(".") && !f.isDirectory()) {
                            fileHashes.put(shaderPackPath.relativize(path).toString(), SHA256.calculateSHA256(path.toFile()));
                        }
                    } catch (IOException | NoSuchAlgorithmException e) {
                        LOGGER.error("Failed to open file for hash calculation", e);
                    }
                });
            }
            return compare(name, fileHashes);
        } else if (shaderPack.isFile()) {
            return compare(name, Map.of(name, SHA256.calculateSHA256(shaderPack)));
        }
        return false;
    }

    private static boolean compare(String name, Map<String, String> fileHashes) throws IOException {
        InputStream is = Main.class.getResourceAsStream("/" + name + "_sha256");
        if (setupMode)
            try (FileWriter fw = new FileWriter(Iris.getShaderpacksDirectory() + File.separator + name + "_sha256")) {
                GSON.toJson(fileHashes, fw);
                fw.flush();
            }
        if (is != null) {
            return fileHashes.equals(GSON.fromJson(new InputStreamReader(is), new TypeToken<Map<String, String>>() {}.getType()));
        }
        return false;
    }

    public static void read() {
        try (FileReader reader = new FileReader(filePath.toFile())) {
            setupMode = GSON.fromJson(reader, Boolean.class);
        } catch (FileNotFoundException | JsonSyntaxException e) {
            LOGGER.info("File not found");
        } catch (IOException e) {
            LOGGER.error("Failed to read file", e);
        }
    }
}
