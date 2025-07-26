package me.pajic.affogatotweaks.mixson;

import com.google.gson.JsonElement;
import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import net.fabricmc.loader.api.FabricLoader;
import net.ramixin.mixson.inline.Mixson;

import java.util.Map;

public class RecipeEvents {
    private static final Object2DoubleMap<String> RESULT_XP_MAP = new Object2DoubleArrayMap<>(Map.ofEntries(
            Map.entry("minecraft:iron_ingot", 1.5),
            Map.entry("minecraft:copper_ingot", 1.0),
            Map.entry("minecraft:netherite_scrap", 10.0),
            Map.entry("minecraft:iron_nugget", 1.0),
            Map.entry("minecraft:gold_nugget", 1.0),
            Map.entry("minecraft:terracotta", 1.6),
            Map.entry("minecraft:brick", 0.4)
    ));
    private static final Object2DoubleMap<String> INPUT_XP_MAP = new Object2DoubleArrayMap<>(Map.ofEntries(
            Map.entry("minecraft:raw_gold", 3.0),
            Map.entry("minecraft:gold_ore", 3.0),
            Map.entry("minecraft:deepslate_gold_ore", 3.0),
            Map.entry("minecraft:nether_gold_ore", 0.75)
    ));
    public static void register() {
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.getPath().startsWith("recipe/") && !rl.getNamespace().equals("emi"),
                "Modify smelting and blasting recipe XP rewards",
                context -> {
                    String type = context.getFile().getAsJsonObject().getAsJsonPrimitive("type").getAsString();
                    if (context.getFile().getAsJsonObject().has("experience")) {
                        if (type.equals("minecraft:campfire_cooking") || type.equals("minecraft:smoking"))
                            context.getFile().getAsJsonObject().remove("experience");
                        if (type.equals("minecraft:smelting") || type.equals("minecraft:blasting")) {
                            String resultItem = context.getFile().getAsJsonObject().getAsJsonObject("result").getAsJsonPrimitive("id").getAsString();
                            if (RESULT_XP_MAP.containsKey(resultItem))
                                context.getFile().getAsJsonObject().addProperty("experience", RESULT_XP_MAP.getDouble(resultItem));
                            else {
                                JsonElement inputItems = context.getFile().getAsJsonObject().getAsJsonObject("ingredient");
                                if (!inputItems.isJsonArray() && inputItems.getAsJsonObject().has("item")) {
                                    String inputItem = inputItems.getAsJsonObject().getAsJsonPrimitive("item").getAsString();
                                    if (INPUT_XP_MAP.containsKey(inputItem))
                                        context.getFile().getAsJsonObject().addProperty("experience", INPUT_XP_MAP.getDouble(inputItem));
                                    else context.getFile().getAsJsonObject().remove("experience");
                                }
                                else context.getFile().getAsJsonObject().remove("experience");
                            }
                        }
                    }
                },
                true
        );
        if (FabricLoader.getInstance().isModLoaded("farmersdelight")) Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:recipe/cake") || rl.toString().equals("farmersdelight:recipe/cake_from_milk_bottle"),
                "Remove other cake recipes",
                context -> context.markForDeletion(true),
                true
        );
        if (FabricLoader.getInstance().isModLoaded("inmis")) Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("inmis:recipe/baby_backpack") || rl.toString().equals("inmis:recipe/endless_backpack") || rl.toString().equals("inmis:recipe/withered_backpack"),
                "Remove backpack recipes",
                context -> context.markForDeletion(true),
                true
        );
    }
}
