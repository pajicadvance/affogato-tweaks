package me.pajic.affogatotweaks.mixson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.pajic.affogatotweaks.values.LootValues;
import net.ramixin.mixson.inline.Mixson;

public class LootTableEvents {
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
                                if (LootValues.TOOL_TO_MATERIAL_AMOUNT.containsKey(itemName)) {
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
                                            itemName.contains("diamond") ? "minecraft:diamond" : itemName.contains("iron") ? "minecraft:iron_ingot" : "minecraft:cobblestone"
                                    );
                                    replacementEntry.getAsJsonObject().getAsJsonArray("functions").get(0).getAsJsonObject()
                                            .addProperty("count", LootValues.TOOL_TO_MATERIAL_AMOUNT.getInt(itemName));
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
                          "condition": "minecraft:random_chance"
                        }
                      ]
                    }
                    """).deepCopy();
                    pool.getAsJsonObject()
                            .getAsJsonArray("conditions").get(0).getAsJsonObject()
                            .addProperty("chance", LootValues.TRIAL_EXPLORER_MAP_CHANCE);
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
                      ],
                      "conditions": [
                        {
                          "condition": "minecraft:random_chance"
                        }
                      ]
                    }
                    """).deepCopy();
                    pool.getAsJsonObject()
                            .getAsJsonArray("conditions").get(0).getAsJsonObject()
                            .addProperty("chance", LootValues.GLOBE_BANNER_PATTERN_CHANCE);
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
                          "condition": "minecraft:random_chance"
                        }
                      ]
                    }
                    """).deepCopy();
                    pool.getAsJsonObject()
                            .getAsJsonArray("conditions").get(0).getAsJsonObject()
                            .addProperty("chance", LootValues.WOODLAND_EXPLORER_MAP_CHANCE);
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
                          "condition": "minecraft:random_chance"
                        }
                      ]
                    }
                    """).deepCopy();
                    pool.getAsJsonObject()
                            .getAsJsonArray("conditions").get(0).getAsJsonObject()
                            .addProperty("chance", LootValues.OCEAN_EXPLORER_MAP_CHANCE);
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                },
                true
        );
        LootValues.TOTEM_LOCATION_CHANCE.forEach((key, value) -> Mixson.registerEvent(
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
                          "condition": "minecraft:random_chance"
                        }
                      ]
                    }
                    """).deepCopy();
                    pool.getAsJsonObject()
                            .getAsJsonArray("conditions").get(0).getAsJsonObject()
                            .addProperty("chance", LootValues.CURSE_ENCHANTED_BOOK_CHANCE);
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                },
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:loot_table/chests/igloo_chest") || rl.toString().equals("minecraft:loot_table/chests/ancient_city_ice_box"),
                "Distribute frost walker enchantment to igloo and ice box chests",
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
                              "options": "minecraft:frost_walker"
                            }
                          ]
                        }
                      ],
                      "conditions": [
                        {
                          "condition": "minecraft:random_chance"
                        }
                      ]
                    }
                    """).deepCopy();
                    pool.getAsJsonObject().getAsJsonArray("conditions").get(0).getAsJsonObject()
                            .addProperty("chance", context.getResourceId().getPath().endsWith("igloo_chest.json") ?
                                    LootValues.FROST_WALKER_IGLOO_CHANCE : LootValues.FROST_WALKER_ICE_BOX_CHANCE);
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
                                 "minecraft:impaling",
                                 "minecraft:channeling"
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
                                JsonElement e = JsonParser.parseString("""
                                {
                                  "type": "minecraft:item",
                                  "name": "minecraft:apple",
                                  "conditions": [
                                    {
                                      "condition": "minecraft:random_chance"
                                    }
                                  ]
                                }
                                """).deepCopy();
                                e.getAsJsonObject()
                                        .getAsJsonArray("conditions").get(0).getAsJsonObject()
                                        .addProperty("chance", LootValues.APPLE_DROP_CHANCE);
                                updatedEntries.add(e);
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
