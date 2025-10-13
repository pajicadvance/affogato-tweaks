package me.pajic.affogatotweaks.mixson;

import com.google.gson.JsonArray;
import net.ramixin.mixson.inline.Mixson;

public class AtlasEvents {
    public static void register() {
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:atlases/armor_trims") || rl.toString().equals("minecraft:atlases/blocks"),
                "Modify atlases",
                context -> {
                    JsonArray sources = context.getFile().getAsJsonObject().get("sources").getAsJsonArray();
                    sources.forEach(source -> {
                        if (source.getAsJsonObject().get("type").getAsString().equals("paletted_permutations")) {
                            source.getAsJsonObject().get("permutations").getAsJsonObject()
                                    .addProperty("copper_darker", "trims/color_palettes/copper_darker");
                        }
                    });
                },
                false
        );
    }
}
