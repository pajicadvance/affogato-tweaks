package me.pajic.affogato_core.mixson.events;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import me.pajic.affogato_core.CompatFlags;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.mixson.MixsonHelper;

import java.util.List;
import java.util.Set;

public class TagEvents {

    private static final List<String> EARLY_GAME_DISABLED_TOOLS = List.of(
            "minecraft:stone_pickaxe",
            "minecraft:stone_shovel",
            "minecraft:stone_sword",
            "minecraft:stone_axe",
            "minecraft:stone_hoe",
            "minecraft:stone_spear",
            "minecraft:wooden_pickaxe",
            "minecraft:wooden_shovel",
            "minecraft:wooden_sword",
            "minecraft:wooden_axe",
            "minecraft:wooden_hoe",
            "minecraft:wooden_spear"
    );

    public static void register() {
        if (Main.CONFIG.misc.moreStoneTypesInStoneCraftingRecipes.get()) MixsonHelper.registerMultiJson(
                "Add more stone types to stone crafting recipes",
                Set.of("minecraft:tags/item/stone_crafting_materials", "minecraft:tags/item/stone_tool_materials"),
                context -> {
                    JsonArray values = context.getFile().getAsJsonObject().getAsJsonArray("values");
                    values.add("minecraft:andesite");
                    values.add("minecraft:granite");
                    values.add("minecraft:diorite");
                    values.add("minecraft:tuff");
                }
        );
        if (Main.CONFIG.misc.ironMinesObsidian.get()) {
            MixsonHelper.registerSingleJson(
                    "Allow mining obsidian with iron tools",
                    "minecraft:tags/block/needs_iron_tool",
                    context -> {
                        JsonArray values = context.getFile().getAsJsonObject().getAsJsonArray("values");
                        values.add("minecraft:obsidian");
                        values.add("minecraft:crying_obsidian");
                        values.add("minecraft:respawn_anchor");
                    }
            );
            MixsonHelper.registerSingleJson(
                    "Allow mining obsidian with iron tools",
                    "minecraft:tags/block/needs_diamond_tool",
                    context -> {
                        JsonArray values = context.getFile().getAsJsonObject().getAsJsonArray("values");
                        values.remove(new JsonPrimitive("minecraft:obsidian"));
                        values.remove(new JsonPrimitive("minecraft:crying_obsidian"));
                        values.remove(new JsonPrimitive("minecraft:respawn_anchor"));
                    }
            );
        }
        if (Main.CONFIG.features.affogatoEarlyGameChanges.get()) MixsonHelper.registerSingleJson(
                "Allow mining copper ore with flint tools",
                "minecraft:tags/block/needs_stone_tool",
                context -> {
                    JsonArray values = context.getFile().getAsJsonObject().getAsJsonArray("values");
                    values.remove(new JsonPrimitive("minecraft:copper_ore"));
                    values.remove(new JsonPrimitive("universal_ores:granite_copper_ore"));
                    values.remove(new JsonPrimitive("universal_ores:diorite_copper_ore"));
                    values.remove(new JsonPrimitive("universal_ores:andesite_copper_ore"));
                    values.remove(new JsonPrimitive("universal_ores:tuff_copper_ore"));
                    values.remove(new JsonPrimitive("universal_ores:calcite_copper_ore"));
                }
        );
        if (CompatFlags.SERENE_WILD_LOADED) MixsonHelper.registerMultiJson(
                "Patch Serene Wild tags for newer versions",
                index -> index.toString().startsWith("sereneseasons:tags/") && (index.id().getPath().contains("autumn_crops") || index.id().getPath().contains("unbreakable_infertile_crops")),
                context -> {
                    JsonArray values = context.getFile().getAsJsonObject().getAsJsonArray("values");
                    JsonPrimitive p = new JsonPrimitive("wilderwild:maple_sapling");
                    if (values.contains(p)) {
                        values.remove(p);
                        values.add("wilderwild:yellow_maple_sapling");
                        values.add("wilderwild:orange_maple_sapling");
                        values.add("wilderwild:red_maple_sapling");
                    }
                }
        );
        MixsonHelper.registerSingleJson(
                "Hide disabled items from recipe viewers",
                "c:tags/item/hidden_from_recipe_viewers",
                context -> {
                    Main.CONFIG.hiddenItems.get().forEach(s -> {
                        JsonObject o = new JsonObject();
                        o.addProperty("id", s);
                        o.addProperty("required", false);
                        context.getFile().getAsJsonObject().getAsJsonArray("values").add(o);
                    });
                    if (Main.CONFIG.features.affogatoEarlyGameChanges.get()) EARLY_GAME_DISABLED_TOOLS.forEach(s -> {
                        JsonObject o = new JsonObject();
                        o.addProperty("id", s);
                        o.addProperty("required", false);
                        context.getFile().getAsJsonObject().getAsJsonArray("values").add(o);
                    });
                    if (CompatFlags.HORSEMAN_LOADED) {
                        context.getFile().getAsJsonObject().getAsJsonArray("values").add("thecopperierage:copper_horn");
                    }
                }
        );
    }
}
