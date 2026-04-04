package me.pajic.affogato_core.mixson.events;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.pajic.affogato_core.CompatFlags;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.mixson.MixsonHelper;

import java.util.List;

public class WorldgenDataEvents {

    private static final List<String> ORE = List.of("coal", "copper", "diamond", "emerald", "gold", "iron", "lapis", "nether_gold", "quartz", "redstone");
    private static final List<String> END_BIOMES = List.of(
            "minecraft:end_barrens", "minecraft:end_highlands", "minecraft:end_midlands", "minecraft:small_end_islands", "minecraft:the_end",
            "nullscape:crystal_peaks", "nullscape:shadowlands", "nullscape:void_barrens"
    );

    public static void register() {
        if (Main.CONFIG.features.villagerNuke.get()) MixsonHelper.registerMultiJson(
                "Remove normal village spawns",
                index -> index.id().getPath().startsWith("worldgen/template_pool/village") && index.id().getPath().endsWith("town_centers"),
                context -> {
                    JsonArray elements = context.getFile().getAsJsonObject().getAsJsonArray("elements");
                    JsonArray updatedElements = new JsonArray();
                    elements.forEach(element -> {
                        if (element.getAsJsonObject().getAsJsonObject("element").getAsJsonPrimitive("location").getAsString().contains("zombie")) {
                            updatedElements.add(element);
                        }
                    });
                    context.getFile().getAsJsonObject().add("elements", updatedElements);
                }
        );
        MixsonHelper.registerMultiJson(
                "Increase max ancient debris vein size and allow air exposure",
                index -> index.id().getPath().startsWith("worldgen/configured_feature/ore_ancient_debris"),
                context -> {
                    context.getFile().getAsJsonObject().getAsJsonObject("config").addProperty("discard_chance_on_air_exposure", 0);
                    context.getFile().getAsJsonObject().getAsJsonObject("config").addProperty("size", Main.CONFIG.worldgen.ancientDebrisSize.get());
                }
        );
        if (Main.CONFIG.worldgen.removeAncientDebrisHeightLimit.get()) MixsonHelper.registerMultiJson(
                "Remove height limit for large ancient debris spawns",
                index -> index.id().getPath().startsWith("worldgen/placed_feature/ore_ancient_debris_large"),
                context -> {
                    JsonArray placement = context.getFile().getAsJsonObject().getAsJsonArray("placement");
                    JsonArray updatedPlacement = new JsonArray();
                    placement.forEach(element -> {
                        if (!element.getAsJsonObject().getAsJsonPrimitive("type").getAsString().equals("minecraft:height_range")) {
                            updatedPlacement.add(element);
                        }
                    });
                    updatedPlacement.add(JsonParser.parseString("""
                            {
                                "type": "minecraft:height_range",
                                "height": {
                                    "type": "minecraft:uniform",
                                    "max_inclusive": {
                                      "below_top": 8
                                    },
                                    "min_inclusive": {
                                      "absolute": 8
                                    }
                                }
                            }"""));
                    context.getFile().getAsJsonObject().add("placement", updatedPlacement);
                }
        );
        ORE.forEach(ore -> MixsonHelper.registerMultiJson(
                "Increase " + ore + " ore size",
                index -> index.id().toString().startsWith("minecraft:worldgen/configured_feature/ore_" + ore),
                context -> context.getFile().getAsJsonObject()
                        .getAsJsonObject("config").addProperty("size", Math.round(
                                context.getFile().getAsJsonObject().getAsJsonObject("config")
                                        .getAsJsonPrimitive("size").getAsInt() * Main.CONFIG.worldgen.oreSizeMult.get()))
        ));
        MixsonHelper.registerMultiJson(
                "Increase dungeon spawn rate",
                index -> index.id().toString().startsWith("minecraft:worldgen/placed_feature/monster_room") || index.id().toString().startsWith("repurposed_structures:worldgen/placed_feature/dungeons/"),
                context -> {
                    JsonArray placement = context.getFile().getAsJsonObject().getAsJsonArray("placement");
                    for (JsonElement element : placement) {
                        JsonObject object = element.getAsJsonObject();
                        String type = object.getAsJsonPrimitive("type").getAsString();
                        if (type.equals("minecraft:count") || type.equals("repurposed_structures:unlimited_count")) {
                            object.addProperty("count", Math.round(object.getAsJsonPrimitive("count").getAsInt() * Main.CONFIG.worldgen.dungeonChanceMult.get()));
                        }
                    }
                }
        );
        MixsonHelper.registerMultiJson(
                "Increase mineshaft spawn rate",
                index -> index.id().toString().equals("minecraft:worldgen/structure_set/mineshafts") || index.id().toString().startsWith("repurposed_structures:worldgen/structure_set/mineshafts"),
                context -> context.getFile().getAsJsonObject()
                        .getAsJsonObject("placement").addProperty("frequency",
                                context.getFile().getAsJsonObject().getAsJsonObject("placement")
                                        .getAsJsonPrimitive("frequency").getAsFloat() * Main.CONFIG.worldgen.mineshaftChanceMult.get()
                        )
        );
        MixsonHelper.registerSingleJson(
                "Fix ocelots being counted as monsters during spawning",
                "minecraft:worldgen/biome/jungle",
                context -> {
                    JsonArray monsters = context.getFile().getAsJsonObject()
                            .getAsJsonObject("spawners")
                            .getAsJsonArray("monster");
                    JsonArray creatures = context.getFile().getAsJsonObject()
                            .getAsJsonObject("spawners")
                            .getAsJsonArray("creature");
                    int idToRemove = -1;
                    JsonObject ocelot = new JsonObject();
                    for (int i = 0; i < monsters.size(); i++) {
                        JsonObject monster = monsters.get(i).getAsJsonObject();
                        if (monster.get("type").getAsString().equals("minecraft:ocelot")) {
                            idToRemove = i;
                            ocelot.addProperty("type", "minecraft:ocelot");
                            ocelot.addProperty("maxCount", monster.get("maxCount").getAsInt());
                            ocelot.addProperty("minCount", monster.get("minCount").getAsInt());
                            ocelot.addProperty("weight", monster.get("weight").getAsInt());
                            break;
                        }
                    }
                    if (idToRemove != -1) monsters.remove(idToRemove);
                    if (!ocelot.isEmpty()) creatures.add(ocelot);
                }
        );
        if (CompatFlags.NULLSCAPE_LOADED && Main.CONFIG.misc.nullscapeEndAmbienceEdits.get()) {
            END_BIOMES.forEach(s -> {
                String[] split = s.split(":");
                MixsonHelper.registerSingleJson(
                        "Fix Nullscape biome fog",
                        split[0] + ":worldgen/biome/" + split[1],
                        context -> {
                            if (context.getFile().getAsJsonObject().has("attributes")) {
                                JsonObject attributes = context.getFile().getAsJsonObject().getAsJsonObject("attributes");
                                attributes.remove("minecraft:visual/fog_color");
                                attributes.remove("minecraft:visual/water_fog_color");
                                attributes.remove("minecraft:visual/sky_color");
                            }
                        }
                );
            });
            MixsonHelper.registerSingleJson(
                    "Fix End ambient light with Nullscape",
                    "minecraft:dimension_type/the_end",
                    context -> context.getFile().getAsJsonObject().addProperty("ambient_light", 0.25)
            );
        }
    }
}
