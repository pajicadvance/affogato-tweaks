package me.pajic.affogatotweaks.mixson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.ramixin.mixson.inline.Mixson;

import java.util.Map;

public class LootTableEvents {
    private static final Object2IntMap<String> REPLACEMENT_MAP = new Object2IntArrayMap<>(Map.ofEntries(
            Map.entry("minecraft:diamond_helmet", 5),
            Map.entry("minecraft:diamond_chestplate", 8),
            Map.entry("minecraft:diamond_leggings", 7),
            Map.entry("minecraft:diamond_boots", 4),
            Map.entry("minecraft:diamond_pickaxe", 3),
            Map.entry("minecraft:diamond_axe", 3),
            Map.entry("minecraft:diamond_shovel", 1),
            Map.entry("minecraft:diamond_sword", 2),
            Map.entry("minecraft:diamond_hoe", 2),
            Map.entry("minecraft:iron_helmet", 5),
            Map.entry("minecraft:iron_chestplate", 8),
            Map.entry("minecraft:iron_leggings", 7),
            Map.entry("minecraft:iron_boots", 4),
            Map.entry("minecraft:iron_pickaxe", 3),
            Map.entry("minecraft:iron_axe", 3),
            Map.entry("minecraft:iron_shovel", 1),
            Map.entry("minecraft:iron_sword", 2),
            Map.entry("minecraft:iron_hoe", 2)
    ));
    private static final Object2DoubleMap<String> TOTEM_MAP = new Object2DoubleArrayMap<>(Map.ofEntries(
            Map.entry("jungle_temple", 0.5),
            Map.entry("pillager_outpost", 0.75),
            Map.entry("woodland_mansion", 1.0)
    ));
    public static void register() {
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.getPath().startsWith("loot_table/chests/"),
                "Replace tools and armor in loot chests with materials",
                context -> {
                    JsonArray updatedPools = new JsonArray();
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").forEach(pool -> {
                        JsonObject updatedPool = pool.getAsJsonObject().deepCopy();
                        JsonArray updatedEntries = new JsonArray();
                        pool.getAsJsonObject().getAsJsonArray("entries").forEach(entry -> {
                            if (entry.getAsJsonObject().has("name")) {
                                String itemName = entry.getAsJsonObject().getAsJsonPrimitive("name").getAsString();
                                if (REPLACEMENT_MAP.containsKey(itemName)) {
                                    JsonElement replacementEntry = JsonParser.parseString("""
                                    {
                                      "type": "minecraft:item",
                                      "functions": [
                                        {
                                          "function": "minecraft:set_count"
                                        }
                                      ]
                                    }""").deepCopy();
                                    replacementEntry.getAsJsonObject().addProperty(
                                            "name",
                                            itemName.contains("diamond") ? "minecraft:diamond" : "minecraft:iron_ingot"
                                    );
                                    replacementEntry.getAsJsonObject().getAsJsonArray("functions").get(0).getAsJsonObject()
                                            .addProperty("count", REPLACEMENT_MAP.getInt(itemName));
                                    updatedEntries.add(replacementEntry);
                                }
                                else updatedEntries.add(entry);
                            }
                            else updatedEntries.add(entry);
                        });
                        updatedPool.add("entries", updatedEntries);
                        updatedPools.add(updatedPool);
                    });
                    context.getFile().getAsJsonObject().add("pools", updatedPools);
                },
                true
        );
        TOTEM_MAP.forEach((key, value) -> Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:loot_table/chests/" + key),
                "Distribute Totem of Undying to loot chests",
                context -> {
                    JsonElement totemPool = JsonParser.parseString("""
                    {
                      "rolls": 1.0,
                      "entries": [
                        {
                          "type": "minecraft:item"
                        }
                      ],
                      "conditions": [
                        {
                          "condition": "minecraft:random_chance"
                        }
                      ]
                    }
                    """).deepCopy();
                    totemPool.getAsJsonObject()
                            .getAsJsonArray("entries").get(0).getAsJsonObject()
                            .addProperty("name", "minecraft:totem_of_undying");
                    totemPool.getAsJsonObject()
                            .getAsJsonArray("conditions").get(0).getAsJsonObject()
                            .addProperty("chance", value);
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(totemPool);
                },
                true
        ));
    }
}
