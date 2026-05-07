package me.pajic.affogato_core.iris;

import me.pajic.affogato_core.ClientMain;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class ShaderPresetSetup {

    private static final Path SHADER_DIR = FabricLoader.getInstance().getGameDir().resolve("shaderpacks");
    private static final Path SHADER_PRESETS_DIR = SHADER_DIR.resolve("affogato_presets");
    public static @Nullable String[] presets = null;
    public static boolean allPresetsCreated = false;

    @SuppressWarnings("DataFlowIssue") // listFiles() is always called on files that are guaranteed to be directories
    public static void init() {
        File shaderDir = SHADER_DIR.toFile();
        File shaderPresetsDir = SHADER_PRESETS_DIR.toFile();
        if (shaderDir.exists() && shaderPresetsDir.exists() && shaderDir.isDirectory() && shaderPresetsDir.isDirectory()) {
            presets = new String[shaderPresetsDir.listFiles().length];
            try (Stream<Path> fileStream = Files.walk(SHADER_PRESETS_DIR, 1)) {
                fileStream.filter(path -> !path.toFile().getName().equals("affogato_presets"))
                        .map(path -> path.toFile().getName().split("_", 2))
                        .forEach(split -> {
                    try {
                        presets[Integer.parseInt(split[0])] = split[1].split("\\.")[0];
                    } catch (NumberFormatException e) {
                        ClientMain.LOGGER.error("Invalid preset file name format, skipping", e);
                    }
                });
            } catch (IOException e) {
                ClientMain.LOGGER.error("Can't access file, did not create shader presets", e);
                return;
            }
            if (Arrays.stream(presets).noneMatch(Objects::isNull)) {
                try (Stream<Path> fileStream = Files.walk(SHADER_DIR, 1)) {
                    fileStream.map(Path::toFile).forEach(shader -> {
                        String name = shader.getName();
                        if (name.contains("EuphoriaPatches")) {
                            for (int i = 0; i < presets.length; i++) {
                                String preset = presets[i];
                                if (Arrays.stream(shaderDir.listFiles()).noneMatch(f -> f.getName().equals("Affogato_" + preset + ".zip.txt"))) {
                                    File shaderCopy = SHADER_DIR.resolve("Affogato_" + preset + ".zip").toFile();
                                    try {
                                        if (shader.isDirectory()) FileUtils.copyDirectory(shader, shaderCopy);
                                        else FileUtils.copyFile(shader, shaderCopy);
                                        FileUtils.copyFile(
                                                SHADER_PRESETS_DIR.resolve(i + "_" + preset + ".txt").toFile(),
                                                SHADER_DIR.resolve("Affogato_" + preset + ".zip.txt").toFile()
                                        );
                                        ClientMain.LOGGER.info("Created shader preset {}", preset);
                                    } catch (IOException e) {
                                        ClientMain.LOGGER.error("Couldn't copy preset {}, skipping", preset, e);
                                    }
                                }
                            }
                        }
                    });
                } catch (IOException e) {
                    ClientMain.LOGGER.error("Can't access file, did not create shader presets", e);
                }
            } else {
                ClientMain.LOGGER.warn("Shader preset file name indices not in order, did not create shader presets");
            }
            if (presets != null && Arrays.stream(presets).noneMatch(Objects::isNull) && Arrays.stream(presets).allMatch(preset -> Arrays.stream(shaderDir.listFiles()).anyMatch(file -> file.getName().equals("Affogato_" + preset + ".zip.txt")))) {
                ClientMain.LOGGER.info("Shader presets ready");
                allPresetsCreated = true;
            }
        }
    }
}
