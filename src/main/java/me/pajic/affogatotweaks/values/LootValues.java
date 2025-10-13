package me.pajic.affogatotweaks.values;

import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;

import java.util.Map;

public class LootValues {
    public static final Object2IntMap<String> TOOL_TO_MATERIAL_AMOUNT = new Object2IntArrayMap<>(Map.ofEntries(
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
            Map.entry("minecraft:iron_hoe", 2),
            Map.entry("farmersdelight:diamond_knife", 1),
            Map.entry("farmersdelight:iron_knife", 1),
            Map.entry("minecraft:stone_pickaxe", 3),
            Map.entry("minecraft:stone_shovel", 1),
            Map.entry("minecraft:stone_sword", 2),
            Map.entry("minecraft:stone_axe", 3),
            Map.entry("minecraft:stone_hoe", 2)
    ));
    public static final Object2DoubleMap<String> TOTEM_LOCATION_CHANCE = new Object2DoubleArrayMap<>(Map.ofEntries(
            Map.entry("jungle_temple", 0.5),
            Map.entry("pillager_outpost", 0.75),
            Map.entry("woodland_mansion", 1.0)
    ));

    public static final double TRIAL_EXPLORER_MAP_CHANCE = 0.08;
    public static final double WOODLAND_EXPLORER_MAP_CHANCE = 0.33;
    public static final double OCEAN_EXPLORER_MAP_CHANCE = 0.5;
    public static final double CURSE_ENCHANTED_BOOK_CHANCE = 0.5;
    public static final double FROST_WALKER_IGLOO_CHANCE = 0.5;
    public static final double FROST_WALKER_ICE_BOX_CHANCE = 1;
    public static final double GLOBE_BANNER_PATTERN_CHANCE = 1;
    public static final double APPLE_DROP_CHANCE = 0.025;
}
