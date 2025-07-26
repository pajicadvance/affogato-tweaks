package me.pajic.affogatotweaks.mixson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.objects.*;
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
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:loot_table/chests/simple_dungeon") || rl.toString().equals("minecraft:loot_table/chests/abandoned_mineshaft"),
                "Distribute trial explorer map to dungeons and abandoned mineshafts",
                context -> {
                    JsonElement pool = JsonParser.parseString("""
                    {
                      "rolls": 1,
                      "entries": [
                        {
                          "type": "minecraft:item",
                          "functions": [
                            {
                              "function": "minecraft:exploration_map",
                              "destination": "minecraft:on_trial_chambers_maps",
                              "decoration": "minecraft:trial_chambers",
                              "zoom": 2
                            },
                            {
                              "function": "minecraft:set_name",
                              "name": {
                                "translate": "filled_map.trial_chambers"
                              },
                              "target": "item_name"
                            }
                          ],
                          "name": "minecraft:map"
                        }
                      ],
                      "conditions": [
                        {
                          "condition": "minecraft:random_chance",
                          "chance": 0.08
                        }
                      ]
                    }
                    """).deepCopy();
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                },
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:loot_table/chests/woodland_mansion"),
                "Distribute Globe banner pattern to woodland mansion chest",
                context -> {
                    JsonElement pool = JsonParser.parseString("""
                    {
                      "rolls": 1.0,
                      "entries": [
                        {
                          "type": "minecraft:item",
                          "name": "minecraft:globe_banner_pattern"
                        }
                      ]
                    }
                    """).deepCopy();
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                },
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:loot_table/chests/pillager_outpost"),
                "Distribute woodland explorer map to pillager outpost chest",
                context -> {
                    JsonElement pool = JsonParser.parseString("""
                    {
                      "rolls": 1.0,
                      "entries": [
                        {
                          "type": "minecraft:item",
                          "functions": [
                            {
                              "function": "minecraft:exploration_map",
                              "destination": "minecraft:on_woodland_explorer_maps",
                              "decoration": "minecraft:mansion",
                              "zoom": 2
                            },
                            {
                              "function": "minecraft:set_name",
                              "name": {
                                "translate": "filled_map.mansion"
                              },
                              "target": "item_name"
                            }
                          ],
                          "name": "minecraft:map"
                        }
                      ],
                      "conditions": [
                        {
                          "condition": "minecraft:random_chance",
                          "chance": 0.33
                        }
                      ]
                    }
                    """).deepCopy();
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                },
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:loot_table/chests/shipwreck_map"),
                "Distribute ocean explorer map to shipwreck map chest",
                context -> {
                    JsonElement pool = JsonParser.parseString("""
                    {
                      "rolls": 1,
                      "entries": [
                        {
                          "type": "minecraft:item",
                          "functions": [
                            {
                              "function": "minecraft:exploration_map",
                              "destination": "minecraft:on_ocean_explorer_maps",
                              "decoration": "minecraft:monument",
                              "zoom": 2
                            },
                            {
                              "function": "minecraft:set_name",
                              "name": {
                                "translate": "filled_map.monument"
                              },
                              "target": "item_name"
                            }
                          ],
                          "name": "minecraft:map"
                        }
                      ],
                      "conditions": [
                        {
                          "condition": "minecraft:random_chance",
                          "chance": 0.5
                        }
                      ]
                    }
                    """).deepCopy();
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                },
                true
        );
        TOTEM_MAP.forEach((key, value) -> Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:loot_table/chests/" + key),
                "Distribute Totem of Undying to loot chests",
                context -> {
                    JsonElement pool = JsonParser.parseString("""
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
                    pool.getAsJsonObject()
                            .getAsJsonArray("entries").get(0).getAsJsonObject()
                            .addProperty("name", "minecraft:totem_of_undying");
                    pool.getAsJsonObject()
                            .getAsJsonArray("conditions").get(0).getAsJsonObject()
                            .addProperty("chance", value);
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                },
                true
        ));
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:loot_table/chests/desert_pyramid"),
                "Distribute curse enchantments to desert pyramid chests",
                context -> {
                    JsonElement pool = JsonParser.parseString("""
                    {
                      "rolls": 1,
                      "entries": [
                        {
                          "type": "minecraft:item",
                          "name": "minecraft:book",
                          "functions": [
                            {
                              "function": "minecraft:enchant_randomly",
                              "options": "#minecraft:curse"
                            }
                          ]
                        }
                      ],
                      "conditions": [
                        {
                          "condition": "minecraft:random_chance",
                          "chance": 0.5
                        }
                      ]
                    }
                    """).deepCopy();
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                },
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:loot_table/entities/elder_guardian"),
                "Distribute trident enchantments to elder guardian drops",
                context -> {
                    JsonElement pool = JsonParser.parseString("""
                    {
                      "rolls": 1,
                      "entries": [
                        {
                          "type": "minecraft:item",
                          "name": "minecraft:book",
                          "functions": [
                            {
                              "function": "minecraft:enchant_randomly",
                              "options": [
                                 "minecraft:loyalty",
                                 "minecraft:riptide",
                                 "minecraft:impaling"
                              ]
                            }
                          ]
                        }
                      ]
                    }
                    """).deepCopy();
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                },
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:loot_table/chests/trial_chambers/reward_ominous"),
                "Distribute mace enchantments to trial chamber rewards",
                context -> {
                    JsonElement pool = JsonParser.parseString("""
                    {
                      "rolls": 1,
                      "entries": [
                        {
                          "type": "minecraft:item",
                          "name": "minecraft:book",
                          "functions": [
                            {
                              "function": "minecraft:enchant_randomly",
                              "options": [
                                 "minecraft:density",
                                 "minecraft:breach"
                              ]
                            }
                          ]
                        }
                      ]
                    }
                    """).deepCopy();
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                },
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:loot_table/blocks/oak_leaves") || rl.toString().equals("minecraft:loot_table/blocks/dark_oak_leaves"),
                "Increase apple drop chance",
                context -> {
                    JsonArray pools = context.getFile().getAsJsonObject().getAsJsonArray("pools");
                    for (JsonElement pool : pools) {
                        JsonArray updatedEntries = new JsonArray();
                        JsonArray entries = pool.getAsJsonObject().getAsJsonArray("entries");
                        for (JsonElement entry : entries) {
                            if (entry.getAsJsonObject().has("name") && entry.getAsJsonObject().get("name").getAsString().equals("minecraft:apple")) {
                                updatedEntries = entries.deepCopy();
                                updatedEntries.remove(entry);
                                updatedEntries.add(JsonParser.parseString("""
                                {
                                  "type": "minecraft:item",
                                  "name": "minecraft:apple",
                                  "conditions": [
                                    {
                                      "condition": "minecraft:random_chance",
                                      "chance": 0.025
                                    }
                                  ]
                                }
                                """).deepCopy());
                                break;
                            }
                        }
                        if (!updatedEntries.isEmpty()) {
                            pool.getAsJsonObject().add("entries", updatedEntries);
                            break;
                        }
                    }
                },
                true
        );
    }
}
