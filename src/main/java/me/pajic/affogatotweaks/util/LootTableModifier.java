package me.pajic.affogatotweaks.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.util.Identifier;

public class LootTableModifier {

    private static final Identifier ABANDONED_MINESHAFT = LootTables.ABANDONED_MINESHAFT_CHEST;
    private static final Identifier SIMPLE_DUNGEON = LootTables.SIMPLE_DUNGEON_CHEST;
    private static final Identifier WOODLAND_MANSION = LootTables.WOODLAND_MANSION_CHEST;
    private static final Identifier STRONGHOLD_LIBRARY = LootTables.STRONGHOLD_LIBRARY_CHEST;
    private static final Identifier STRONGHOLD_CORRIDOR = LootTables.STRONGHOLD_CORRIDOR_CHEST;
    private static final Identifier STRONGHOLD_CROSSING = LootTables.STRONGHOLD_CROSSING_CHEST;
    private static final Identifier PILLAGER_OUTPOST = LootTables.PILLAGER_OUTPOST_CHEST;
    private static final Identifier NETHER_BRIDGE = LootTables.NETHER_BRIDGE_CHEST;
    private static final Identifier JUNGLE_TEMPLE = LootTables.JUNGLE_TEMPLE_CHEST;
    private static final Identifier DESERT_PYRAMID = LootTables.DESERT_PYRAMID_CHEST;

    public static void updateLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin()) {
                if (ABANDONED_MINESHAFT.equals(id) || SIMPLE_DUNGEON.equals(id) || STRONGHOLD_CORRIDOR.equals(id) || STRONGHOLD_CROSSING.equals(id) || NETHER_BRIDGE.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .with(ItemEntry.builder(Items.BOOK).weight(50).apply(EnchantRandomlyLootFunction.builder()))
                            .with(EmptyEntry.builder().weight(50));
                    tableBuilder.pool(poolBuilder);
                }
                if (WOODLAND_MANSION.equals(id) || STRONGHOLD_LIBRARY.equals(id) || PILLAGER_OUTPOST.equals(id) || JUNGLE_TEMPLE.equals(id) || DESERT_PYRAMID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.builder()));
                    tableBuilder.pool(poolBuilder);
                }
            }
        });
    }
}
