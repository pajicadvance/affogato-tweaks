package me.pajic.affogatotweaks.values;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;
import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;

import java.util.Map;

public class XpValues {
    public static final int COAL_MAX = 3;
    public static final int NETHER_GOLD_MAX = 3;

    public static final IntIntImmutablePair LAPIS = new IntIntImmutablePair(3, 7);
    public static final IntIntImmutablePair DIAMOND = new IntIntImmutablePair(7, 10);
    public static final IntIntImmutablePair EMERALD = new IntIntImmutablePair(12, 15);
    public static final IntIntImmutablePair REDSTONE = new IntIntImmutablePair(3, 7);
    public static final IntIntImmutablePair QUARTZ = new IntIntImmutablePair(3, 7);

    public static final Object2DoubleMap<String> RECIPE_RESULT_TO_XP = new Object2DoubleArrayMap<>(Map.ofEntries(
            Map.entry("minecraft:iron_ingot", 1.5),
            Map.entry("minecraft:copper_ingot", 1.0),
            Map.entry("minecraft:netherite_scrap", 10.0),
            Map.entry("minecraft:iron_nugget", 1.0),
            Map.entry("minecraft:gold_nugget", 1.0),
            Map.entry("affogatotweaks:copper_nugget", 1.0)
    ));
    public static final Object2DoubleMap<String> RECIPE_INPUT_TO_XP = new Object2DoubleArrayMap<>(Map.ofEntries(
            Map.entry("minecraft:raw_gold", 3.0),
            Map.entry("minecraft:gold_ore", 3.0),
            Map.entry("minecraft:deepslate_gold_ore", 3.0),
            Map.entry("minecraft:nether_gold_ore", 0.75)
    ));
}
