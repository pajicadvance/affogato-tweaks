package me.pajic.affogato_core.mixson;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import me.pajic.affogato_core.Main;
import net.ramixin.mixson.inline.Mixson;

public class TagEvents {
    public static void register() {
        if (Main.CONFIG.misc.moreStoneTypesInStoneCraftingRecipes.get()) Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:tags/item/stone_crafting_materials") || rl.toString().equals("minecraft:tags/item/stone_tool_materials"),
                "Add more stone types to stone crafting recipes",
                context -> {
                    JsonArray values = context.getFile().getAsJsonObject().getAsJsonArray("values");
                    values.add("minecraft:andesite");
                    values.add("minecraft:granite");
                    values.add("minecraft:diorite");
                    values.add("minecraft:tuff");
                },
                true
        );
        if (Main.CONFIG.misc.ironMinesObsidian.get()) {
            Mixson.registerEvent(
                    Mixson.DEFAULT_PRIORITY,
                    rl -> rl.toString().equals("minecraft:tags/block/needs_iron_tool"),
                    "Allow mining obsidian with iron tools",
                    context -> {
                        JsonArray values = context.getFile().getAsJsonObject().getAsJsonArray("values");
                        values.add("minecraft:obsidian");
                        values.add("minecraft:crying_obsidian");
                        values.add("minecraft:respawn_anchor");
                    },
                    true
            );
            Mixson.registerEvent(
                    Mixson.DEFAULT_PRIORITY,
                    rl -> rl.toString().equals("minecraft:tags/block/needs_diamond_tool"),
                    "Allow mining obsidian with iron tools",
                    context -> {
                        JsonArray values = context.getFile().getAsJsonObject().getAsJsonArray("values");
                        values.remove(new JsonPrimitive("minecraft:obsidian"));
                        values.remove(new JsonPrimitive("minecraft:crying_obsidian"));
                        values.remove(new JsonPrimitive("minecraft:respawn_anchor"));
                    },
                    true
            );
        }
        if (Main.CONFIG.features.stoneToolNuke.get()) Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:tags/block/needs_stone_tool"),
                "Allow mining copper ore with wood tools",
                context ->
                        context.getFile().getAsJsonObject().getAsJsonArray("values")
                                .remove(new JsonPrimitive("minecraft:copper_ore")),
                true
        );
    }
}
