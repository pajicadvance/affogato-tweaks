package me.pajic.affogatotweaks.mixson;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import net.ramixin.mixson.inline.Mixson;

public class TagEvents {
    public static void register() {
        Mixson.registerEvent(
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
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:tags/enchantment/non_treasure"),
                "Remove select enchantments from random loot",
                context -> {
                    JsonArray values = context.getFile().getAsJsonObject().getAsJsonArray("values");
                    values.remove(new JsonPrimitive("minecraft:density"));
                    values.remove(new JsonPrimitive("minecraft:breach"));
                    values.remove(new JsonPrimitive("minecraft:loyalty"));
                    values.remove(new JsonPrimitive("minecraft:impaling"));
                    values.remove(new JsonPrimitive("minecraft:riptide"));
                    values.remove(new JsonPrimitive("guarding:barbed"));
                    values.remove(new JsonPrimitive("guarding:pummeling"));
                },
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:tags/enchantment/in_enchanting_table"),
                "Add select enchantments to enchanting table",
                context -> {
                    JsonArray values = context.getFile().getAsJsonObject().getAsJsonArray("values");
                    values.add(new JsonPrimitive("minecraft:density"));
                    values.add(new JsonPrimitive("minecraft:breach"));
                    values.add(new JsonPrimitive("minecraft:loyalty"));
                    values.add(new JsonPrimitive("minecraft:impaling"));
                    values.add(new JsonPrimitive("minecraft:riptide"));
                    values.add(new JsonPrimitive("guarding:barbed"));
                    values.add(new JsonPrimitive("guarding:pummeling"));
                },
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:tags/enchantment/on_random_loot"),
                "Remove curses and frost walker from random loot",
                context -> {
                    JsonArray values = context.getFile().getAsJsonObject().getAsJsonArray("values");
                    values.remove(new JsonPrimitive("minecraft:binding_curse"));
                    values.remove(new JsonPrimitive("minecraft:vanishing_curse"));
                    values.remove(new JsonPrimitive("minecraft:frost_walker"));
                },
                true
        );
    }
}
