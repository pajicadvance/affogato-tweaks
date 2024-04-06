package me.pajic.affogatotweaks.mixin.iris;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import me.pajic.affogatotweaks.AffogatoTweaks;
import me.pajic.affogatotweaks.util.SHA256Util;
import net.coderbot.iris.Iris;
import net.coderbot.iris.gui.element.ShaderPackSelectionList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

// Intercepts the shader pack list refresh in Iris to prevent listing shaders
// which don't match any of the hashes provided by the whitelist.
// For folder shader packs, hashes of each individual file in the folder are stored
// in a json file, and compared against the loaded file hashes.
@Mixin(value = ShaderPackSelectionList.class, remap = false)
public class ShaderPackSelectionListMixin {

    @Unique
    private static final Logger LOGGER = LoggerFactory.getLogger("AffogatoTweaks-ShaderPackSelectionListMixin");

    @Unique
    private static final List<String> ALLOWED_SHADER_PACK_ZIPS = Arrays.asList(
            "f4925044d6975225296cb9cb84b3ee9388bc00d3aacce066eee92bc427ca66cf", // Complementary Reimagined 5.1.1
            "70d8602d8743925b0d12eb351f3fb8b8fd5298a5d0b44eb2a9833cf8ddef013a", // Rethinking Voxels r0.1 beta 2
            "75fef8003f1a9f70eeca4849d0ac526e75e1220f55790f32f23e061bce1c5a28" // Rethinking Voxels r0.1 beta 3 fix 1
                                                                                // Euphoria Patches 1.2 (json)
    );

    @Unique
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Inject(method = "addPackEntry", at = @At("HEAD"), cancellable = true)
    private void matchShaderPack(int index, String name, CallbackInfo ci) throws NoSuchAlgorithmException, IOException {
        Path shaderPackPath = Paths.get(Iris.getShaderpacksDirectory() + File.separator + name);
        File shaderPack = shaderPackPath.toFile();
        if (shaderPack.isDirectory()) {
            InputStream is = AffogatoTweaks.class.getResourceAsStream("/" + name + "_sha256");
            if (is != null) {
                Map<String, String> fileHashes = new Object2ObjectArrayMap<>();
                try (Stream<Path> fileStream = Files.walk(shaderPackPath)) {
                    fileStream.forEach(path -> {
                        try {
                            fileHashes.put(shaderPackPath.relativize(path).toString(), SHA256Util.calculateSHA256(path.toFile()));
                        } catch (IOException | NoSuchAlgorithmException e) {
                            LOGGER.error("Failed to open file for hash calculation", e);
                        }
                    });
                }
                Reader reader = new InputStreamReader(is);
                Map<String, String> storedFileHashes = GSON.fromJson(reader, new TypeToken<Map<String, String>>() {}.getType());
                if (!fileHashes.equals(storedFileHashes)) {
                    ci.cancel();
                }
            }
            else ci.cancel();
        }
        else if (shaderPack.isFile()) {
            if (!ALLOWED_SHADER_PACK_ZIPS.contains(SHA256Util.calculateSHA256(shaderPack))) {
                ci.cancel();
            }
        }
    }
}
