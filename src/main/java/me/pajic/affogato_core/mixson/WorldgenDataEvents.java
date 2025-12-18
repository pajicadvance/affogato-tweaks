package me.pajic.affogato_core.mixson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.pajic.affogato_core.Main;
import net.ramixin.mixson.inline.Mixson;

import java.util.List;

public class WorldgenDataEvents {
    private static final List<String> ORE = List.of("coal", "copper", "diamond", "emerald", "gold", "iron", "lapis", "nether_gold", "quartz", "redstone");
    public static void register() {
        if (Main.CONFIG.features.villagerNuke.get()) Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.getPath().startsWith("worldgen/template_pool/village") && rl.getPath().endsWith("town_centers"),
                "Remove normal village spawns",
                context -> {
                    JsonArray elements = context.getFile().getAsJsonObject().getAsJsonArray("elements");
                    JsonArray updatedElements = new JsonArray();
                    elements.forEach(element -> {
                        if (element.getAsJsonObject().getAsJsonObject("element").getAsJsonPrimitive("location").getAsString().contains("zombie")) {
                            updatedElements.add(element);
                        }
                    });
                    context.getFile().getAsJsonObject().add("elements", updatedElements);
                },
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.getPath().startsWith("worldgen/configured_feature/ore_ancient_debris"),
                "Increase max ancient debris vein size and allow air exposure",
                context -> {
                    context.getFile().getAsJsonObject().getAsJsonObject("config").addProperty("discard_chance_on_air_exposure", 0);
                    context.getFile().getAsJsonObject().getAsJsonObject("config").addProperty("size", Main.CONFIG.worldgen.ancientDebrisSize.get());
                },
                true
        );
        if (Main.CONFIG.worldgen.removeAncientDebrisHeightLimit.get()) Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.getPath().startsWith("worldgen/placed_feature/ore_ancient_debris_large"),
                "Remove height limit for large ancient debris spawns",
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
                },
                true
        );
        ORE.forEach(ore -> Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().startsWith("minecraft:worldgen/configured_feature/ore_" + ore),
                "Increase " + ore + " ore size",
                context -> context.getFile().getAsJsonObject()
                        .getAsJsonObject("config").addProperty("size", Math.round(
                                context.getFile().getAsJsonObject().getAsJsonObject("config")
                                        .getAsJsonPrimitive("size").getAsInt() * Main.CONFIG.worldgen.oreSizeMult.get())),
                true
        ));
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().startsWith("minecraft:worldgen/placed_feature/monster_room") || rl.toString().startsWith("repurposed_structures:worldgen/placed_feature/dungeons/"),
                "Increase dungeon spawn rate",
                context -> {
                    JsonArray placement = context.getFile().getAsJsonObject().getAsJsonArray("placement");
                    for (JsonElement element : placement) {
                        JsonObject object = element.getAsJsonObject();
                        String type = object.getAsJsonPrimitive("type").getAsString();
                        if (type.equals("minecraft:count") || type.equals("repurposed_structures:unlimited_count")) {
                            object.addProperty("count", Math.round(object.getAsJsonPrimitive("count").getAsInt() * Main.CONFIG.worldgen.dungeonChanceMult.get()));
                        }
                    }
                },
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:worldgen/structure_set/mineshafts") || rl.toString().startsWith("repurposed_structures:worldgen/structure_set/mineshafts"),
                "Increase mineshaft spawn rate",
                context -> context.getFile().getAsJsonObject()
                        .getAsJsonObject("placement").addProperty("frequency",
                                context.getFile().getAsJsonObject().getAsJsonObject("placement")
                                        .getAsJsonPrimitive("frequency").getAsFloat() * Main.CONFIG.worldgen.mineshaftChanceMult.get()
                        ),
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:worldgen/biome/jungle"),
                "Fix ocelots being counted as monsters during spawning",
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
                },
                true
        );
    }
}
