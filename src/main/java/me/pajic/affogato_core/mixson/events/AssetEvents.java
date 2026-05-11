package me.pajic.affogato_core.mixson.events;

import me.pajic.affogato_core.config.ModClientConfigHolder;
import me.pajic.affogato_core.mixson.MixsonHelper;

public class AssetEvents {

    public static void register() {
        MixsonHelper.registerMultiJson(
                "Disable BetterGrass layer feature",
                index -> index.id().getPath().startsWith("bettergrass/layer_types/"),
                context -> {
                    if (ModClientConfigHolder.options().disableLBGLayers) context.markForDeletion(true);
                }
        );
    }
}
