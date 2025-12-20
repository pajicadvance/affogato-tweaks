package me.pajic.affogato_core.mixson;

import com.google.gson.*;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.util.LootEntryReplacement;
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
                                if (Main.CONFIG.loot.lootEntryReplacements.get().containsKey(itemName)) {
                                    JsonElement replacementEntry = JsonParser.parseString("""
                                    {
                                      "type": "minecraft:item",
                                      "functions": [
                                        {
                                          "function": "minecraft:set_count"
                                        }
                                      ]
                                    }""").deepCopy();
                                    LootEntryReplacement replacement = Main.CONFIG.loot.lootEntryReplacements.get().get(itemName);
                                    replacementEntry.getAsJsonObject().addProperty("name", replacement.replacementItem.get());
                                    replacementEntry.getAsJsonObject().getAsJsonArray("functions").get(0).getAsJsonObject()
                                            .addProperty("count", replacement.replacementCount.get());
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
                false
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
                            .addProperty("chance", Main.CONFIG.loot.trialExplorerMapChance.get());
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                },
                false
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
                            .addProperty("chance", Main.CONFIG.loot.globeBannerPatternChance.get());
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                },
                false
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
                            .addProperty("chance", Main.CONFIG.loot.woodlandExplorerMapChance.get());
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                },
                false
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
                            .addProperty("chance", Main.CONFIG.loot.oceanExplorerMapChance.get());
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                },
                false
        );
        Main.CONFIG.loot.totemMap.get().forEach((key, value) -> Mixson.registerEvent(
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
                false
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
                            .addProperty("chance", Main.CONFIG.loot.curseEnchantedBookChance.get());
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                },
                false
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
                                    Main.CONFIG.loot.frostWalkerIglooChance.get() : Main.CONFIG.loot.frostWalkerIceBoxChance.get());
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                },
                false
        );
        if (Main.CONFIG.loot.tridentEnchantmentsFromElderGuardians.get()) Mixson.registerEvent(
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
                false
        );
        if (Main.CONFIG.loot.maceEnchantmentsInOminousVaults.get()) Mixson.registerEvent(
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
                false
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:tags/enchantment/non_treasure"),
                "Remove enchantments from non treasure tag",
                context -> {
                    JsonArray values = context.getFile().getAsJsonObject().getAsJsonArray("values");
                    if (Main.CONFIG.loot.maceEnchantmentsInOminousVaults.get()) {
                        values.remove(new JsonPrimitive("minecraft:density"));
                        values.remove(new JsonPrimitive("minecraft:breach"));
                    }
                    if (Main.CONFIG.loot.tridentEnchantmentsFromElderGuardians.get()) {
                        values.remove(new JsonPrimitive("minecraft:loyalty"));
                        values.remove(new JsonPrimitive("minecraft:impaling"));
                        values.remove(new JsonPrimitive("minecraft:riptide"));
                        values.remove(new JsonPrimitive("minecraft:channeling"));
                    }
                },
                false
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:tags/enchantment/on_random_loot"),
                "Remove enchantments from random loot tag",
                context -> {
                    JsonArray values = context.getFile().getAsJsonObject().getAsJsonArray("values");
                    if (Main.CONFIG.loot.frostWalkerOnlyInIgloosAndIceBox.get()) {
                        values.remove(new JsonPrimitive("minecraft:frost_walker"));
                    }
                    if (Main.CONFIG.loot.cursesOnlyInDesertPyramids.get()) {
                        values.remove(new JsonPrimitive("minecraft:binding_curse"));
                        values.remove(new JsonPrimitive("minecraft:vanishing_curse"));
                    }
                },
                false
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
                                        .addProperty("chance", Main.CONFIG.loot.appleDropChance.get());
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
                false
        );
    }
}
