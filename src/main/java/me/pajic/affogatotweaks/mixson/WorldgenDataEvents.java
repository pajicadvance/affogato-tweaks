package me.pajic.affogatotweaks.mixson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.pajic.affogatotweaks.values.WorldgenValues;
import net.ramixin.mixson.inline.Mixson;

import java.util.List;

public class WorldgenDataEvents {
    private static final List<String> ORE = List.of("coal", "copper", "diamond", "emerald", "gold", "iron", "lapis", "nether_gold", "quartz", "redstone");
    public static void register() {
        Mixson.registerEvent(
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
                    context.getFile().getAsJsonObject().getAsJsonObject("config").addProperty("size", WorldgenValues.ANCIENT_DEBRIS_SIZE);
                },
                true
        );
        Mixson.registerEvent(
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
                                        .getAsJsonPrimitive("size").getAsInt() * WorldgenValues.ORE_SIZE_MULT)),
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
                            object.addProperty("count", Math.round(object.getAsJsonPrimitive("count").getAsInt() * WorldgenValues.DUNGEON_CHANCE_MULT));
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
                                        .getAsJsonPrimitive("frequency").getAsFloat() * WorldgenValues.MINESHAFT_CHANCE_MULT
                        ),
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:worldgen/biome/soul_sand_valley"),
                "Replace skeletons with wither skeletons in soul sand valleys",
                context -> {
                    JsonObject spawnCosts = context.getFile().getAsJsonObject().getAsJsonObject("spawn_costs");
                    if (spawnCosts.has("minecraft:skeleton")) {
                        double charge = spawnCosts.getAsJsonObject("minecraft:skeleton").get("charge").getAsDouble();
                        double energy_budget = spawnCosts.getAsJsonObject("minecraft:skeleton").get("energy_budget").getAsDouble();
                        spawnCosts.remove("minecraft:skeleton");
                        JsonObject witherSkeleton = new JsonObject();
                        witherSkeleton.addProperty("charge", charge);
                        witherSkeleton.addProperty("energy_budget", energy_budget);
                        spawnCosts.add("minecraft:wither_skeleton", witherSkeleton);
                    }
                    JsonArray monsters = context.getFile().getAsJsonObject().getAsJsonObject("spawners").getAsJsonArray("monster");
                    int idToRemove = -1;
                    JsonObject witherSkeleton = new JsonObject();
                    for (int i = 0; i < monsters.size(); i++) {
                        JsonObject monster = monsters.get(i).getAsJsonObject();
                        if (monster.get("type").getAsString().equals("minecraft:skeleton")) {
                            idToRemove = i;
                            witherSkeleton.addProperty("type", "minecraft:wither_skeleton");
                            witherSkeleton.addProperty("maxCount", monster.get("maxCount").getAsInt());
                            witherSkeleton.addProperty("minCount", monster.get("minCount").getAsInt());
                            witherSkeleton.addProperty("weight", monster.get("weight").getAsInt());
                            break;
                        }
                    }
                    if (idToRemove != -1) monsters.remove(idToRemove);
                    if (!witherSkeleton.isEmpty()) monsters.add(witherSkeleton);
                },
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:worldgen/structure/fortress"),
                "Replace skeletons with wither skeletons in fortresses",
                context -> {
                    JsonArray spawns = context.getFile().getAsJsonObject()
                            .getAsJsonObject("spawn_overrides")
                            .getAsJsonObject("monster")
                            .getAsJsonArray("spawns");
                    int idToRemove = -1;
                    int idToUpdate = -1;
                    int addWeight = 0;
                    for (int i = 0; i < spawns.size(); i++) {
                        JsonObject spawn = spawns.get(i).getAsJsonObject();
                        if (spawn.get("type").getAsString().equals("minecraft:wither_skeleton")) idToUpdate = i;
                        if (spawn.get("type").getAsString().equals("minecraft:skeleton")) {
                            idToRemove = i;
                            addWeight = spawn.get("weight").getAsInt();
                        }
                    }
                    if (idToRemove != -1) spawns.remove(idToRemove);
                    if (idToUpdate != -1) {
                        JsonObject update = spawns.get(idToUpdate).getAsJsonObject();
                        update.addProperty("weight", update.get("weight").getAsInt() + addWeight);
                    }
                },
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
