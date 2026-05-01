package me.pajic.affogato_core.mixson.events;

import com.google.gson.*;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.mixson.MixsonHelper;
import me.pajic.affogato_core.util.LootEntryReplacement;

import java.util.Set;

public class LootTableEvents {
    public static void register() {
        MixsonHelper.registerMultiJson(
                "Replace tools and armor in loot chests with materials",
                index -> index.id().getPath().startsWith("loot_table/chests/"),
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
                }
        );
        MixsonHelper.registerMultiJson(
                "Distribute trial explorer map to dungeons and abandoned mineshafts",
                Set.of("minecraft:loot_table/chests/simple_dungeon", "minecraft:loot_table/chests/abandoned_mineshaft"),
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
                }
        );
        MixsonHelper.registerSingleJson(
                "Distribute Globe banner pattern to woodland mansion chest",
                "minecraft:loot_table/chests/woodland_mansion",
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
                }
        );
        MixsonHelper.registerMultiJson(
                "Distribute woodland explorer map to pillager outpost chest",
                Set.of("minecraft:loot_table/chests/pillager_outpost", "nova_structures:loot_table/chests/pillager_outpost_treasure"),
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
                }
        );
        MixsonHelper.registerSingleJson(
                "Distribute ocean explorer map to shipwreck map chest",
                "minecraft:loot_table/chests/shipwreck_map",
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
                }
        );
        Main.CONFIG.loot.totemMap.get().forEach((key, value) -> MixsonHelper.registerSingleJson(
                "Distribute Totem of Undying to loot chests",
                "minecraft:loot_table/chests/" + key,
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
                }
        ));
        MixsonHelper.registerMultiJson(
                "Distribute curse enchantments to desert pyramid chests",
                Set.of("minecraft:loot_table/chests/desert_pyramid", "nova_structures:loot_table/chests/desert_temple/desert_temple_lesser"),
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
                }
        );
        MixsonHelper.registerMultiJson(
                "Distribute frost walker enchantment to igloo and ice box chests",
                Set.of("minecraft:loot_table/chests/igloo_chest", "minecraft:loot_table/chests/ancient_city_ice_box", "nova_structures:loot_table/chests/ancient_city_ice_box"),
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
                            .addProperty("chance", context.getIndex().id().getPath().endsWith("igloo_chest.json") ?
                                    Main.CONFIG.loot.frostWalkerIglooChance.get() : Main.CONFIG.loot.frostWalkerIceBoxChance.get());
                    context.getFile().getAsJsonObject().getAsJsonArray("pools").add(pool);
                }
        );
        if (Main.CONFIG.loot.tridentEnchantmentsFromElderGuardians.get()) MixsonHelper.registerSingleJson(
                "Distribute trident enchantments to elder guardian drops",
                "minecraft:loot_table/entities/elder_guardian",
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
                }
        );
        if (Main.CONFIG.loot.maceEnchantmentsInOminousVaults.get()) MixsonHelper.registerSingleJson(
                "Distribute mace enchantments to trial chamber rewards",
                "minecraft:loot_table/chests/trial_chambers/reward_ominous",
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
                }
        );
        MixsonHelper.registerSingleJson(
                "Remove enchantments from non treasure tag",
                "minecraft:tags/enchantment/non_treasure",
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
                }
        );
        MixsonHelper.registerSingleJson(
                "Remove enchantments from random loot tag",
                "minecraft:tags/enchantment/on_random_loot",
                context -> {
                    JsonArray values = context.getFile().getAsJsonObject().getAsJsonArray("values");
                    if (Main.CONFIG.loot.frostWalkerOnlyInIgloosAndIceBox.get()) {
                        values.remove(new JsonPrimitive("minecraft:frost_walker"));
                    }
                    if (Main.CONFIG.loot.cursesOnlyInDesertPyramids.get()) {
                        values.remove(new JsonPrimitive("minecraft:binding_curse"));
                        values.remove(new JsonPrimitive("minecraft:vanishing_curse"));
                    }
                }
        );
    }
}
