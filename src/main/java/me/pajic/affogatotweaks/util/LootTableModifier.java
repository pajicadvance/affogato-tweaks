package me.pajic.affogatotweaks.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.Registries;
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
    private static final Identifier ANCIENT_CITY = LootTables.ANCIENT_CITY_CHEST;
    private static final Identifier ANCIENT_CITY_ICE_BOX = LootTables.ANCIENT_CITY_ICE_BOX_CHEST;
    private static final Identifier END_CITY = LootTables.END_CITY_TREASURE_CHEST;
    private static final Identifier CAT_GIFT = LootTables.CAT_MORNING_GIFT_GAMEPLAY;
    private static final Identifier BURIED_TREASURE = LootTables.BURIED_TREASURE_CHEST;
    private static final Identifier BIG_UNDERWATER_RUIN = LootTables.UNDERWATER_RUIN_BIG_CHEST;
    private static final Identifier SMALL_UNDERWATER_RUIN = LootTables.UNDERWATER_RUIN_SMALL_CHEST;
    private static final Identifier BASTION_OTHER = LootTables.BASTION_OTHER_CHEST;
    private static final Identifier OCEAN_MONUMENT_UPPER_SIDE_CHAMBER = new Identifier("betteroceanmonuments", "chests/upper_side_chamber");
    private static final Identifier JUNGLE_TEMPLE_TREASURE = new Identifier("betterjungletemples", "chests/treasure");
    private static final Identifier EGG_ROOM = new Identifier("betterdungeons", "spider_dungeon/chests/egg_room");
    private static final Identifier SKELETON_DUNGEON_COMMON = new Identifier("betterdungeons", "skeleton_dungeon/chests/common");
    private static final Identifier SKELETON_DUNGEON_MIDDLE = new Identifier("betterdungeons", "skeleton_dungeon/chests/middle");
    private static final Identifier ZOMBIE_DUNGEON_COMMON = new Identifier("betterdungeons", "zombie_dungeon/chests/common");
    private static final Identifier ZOMBIE_DUNGEON_SPECIAL = new Identifier("betterdungeons", "zombie_dungeon/chests/special");
    private static final Identifier ZOMBIE_DUNGEON_TOMBSTONE = new Identifier("betterdungeons", "zombie_dungeon/chests/tombstone");
    private static final Identifier SMALL_NETHER_DUNGEON = new Identifier("betterdungeons", "small_nether_dungeon/chests/common");
    private static final Identifier NETHER_FORTRESS_KEEP = new Identifier("betterfortresses", "chests/keep");
    private static final Identifier NETHER_FORTRESS_BEACON = new Identifier("betterfortresses", "chests/beacon");
    private static final Identifier ELDER_GUARDIAN = new Identifier("minecraft", "entities/elder_guardian");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin()) {
                if (WOODLAND_MANSION.equals(id) || STRONGHOLD_LIBRARY.equals(id) || JUNGLE_TEMPLE.equals(id)
                        || DESERT_PYRAMID.equals(id) || ABANDONED_MINESHAFT.equals(id) || SIMPLE_DUNGEON.equals(id)
                        || STRONGHOLD_CORRIDOR.equals(id) || STRONGHOLD_CROSSING.equals(id) || ANCIENT_CITY.equals(id)) {
                    addRandomEnchantedBook(tableBuilder);
                    addExperienceBottle(tableBuilder, 1);
                    addRandomMusicDisc(tableBuilder);
                }
                if (JUNGLE_TEMPLE_TREASURE.equals(id)) {
                    addRandomEnchantedBook(tableBuilder);
                    addRandomEnchantedBook(tableBuilder);
                    addExperienceBottle(tableBuilder, 3);
                    addRandomMusicDisc(tableBuilder);
                }
                if (CAT_GIFT.equals(id)) {
                    addRandomMusicDisc(tableBuilder);
                }
                if (END_CITY.equals(id)) {
                    EnchantRandomlyLootFunction.Builder builder = EnchantRandomlyLootFunction.create()
                            .add(Registries.ENCHANTMENT.get(new Identifier("enchantedshulkers", "siphon")))
                            .add(Registries.ENCHANTMENT.get(new Identifier("enchantedshulkers", "refill")))
                            .add(Registries.ENCHANTMENT.get(new Identifier("enchantedshulkers", "vacuum")))
                            .add(Registries.ENCHANTMENT.get(new Identifier("enchantedshulkers", "void")))
                            .add(Registries.ENCHANTMENT.get(new Identifier("enchantedshulkers", "augment")))
                            .add(Registries.ENCHANTMENT.get(new Identifier("aileron", "smokestack")))
                            .add(Registries.ENCHANTMENT.get(new Identifier("aileron", "cloudskipper")));
                    addEnchantedBookFromList(tableBuilder, builder);
                    addEnchantedBookFromList(tableBuilder, builder);
                    addExperienceBottle(tableBuilder, 2);
                }
                if (ANCIENT_CITY_ICE_BOX.equals(id)) {
                    addEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.FROST_WALKER)
                    );
                }
                if (PILLAGER_OUTPOST.equals(id)) {
                    addEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.PIERCING)
                            .add(Enchantments.MULTISHOT)
                            .add(Enchantments.QUICK_CHARGE)
                    );
                    addExperienceBottle(tableBuilder, 2);
                }
                if (ELDER_GUARDIAN.equals(id) || OCEAN_MONUMENT_UPPER_SIDE_CHAMBER.equals(id)) {
                    addEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.LOYALTY)
                            .add(Enchantments.IMPALING)
                            .add(Enchantments.RIPTIDE)
                            .add(Enchantments.CHANNELING)
                    );
                    addExperienceBottle(tableBuilder, 1);
                }
                if (EGG_ROOM.equals(id)) {
                    addEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.BANE_OF_ARTHROPODS)
                    );
                    addExperienceBottle(tableBuilder, 1);
                }
                if (BURIED_TREASURE.equals(id)) {
                    addEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.RESPIRATION)
                            .add(Enchantments.AQUA_AFFINITY)
                    );
                    addExperienceBottle(tableBuilder, 2);
                }
                if (BIG_UNDERWATER_RUIN.equals(id)) {
                    addEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.DEPTH_STRIDER)
                    );
                    addExperienceBottle(tableBuilder, 1);
                }
                if (SMALL_UNDERWATER_RUIN.equals(id)) {
                    add1in2ChanceEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.LURE)
                            .add(Enchantments.LUCK_OF_THE_SEA)
                    );
                    add1in2ChanceExperienceBottle(tableBuilder);
                }
                if (SKELETON_DUNGEON_COMMON.equals(id) || ZOMBIE_DUNGEON_COMMON.equals(id) || SKELETON_DUNGEON_MIDDLE.equals(id)) {
                    add1in2ChanceEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.SMITE)
                    );
                    add1in2ChanceExperienceBottle(tableBuilder);
                }
                if (ZOMBIE_DUNGEON_SPECIAL.equals(id) || ZOMBIE_DUNGEON_TOMBSTONE.equals(id)) {
                    addEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.SMITE)
                    );
                    addExperienceBottle(tableBuilder, 1);
                }
                if (NETHER_BRIDGE.equals(id) || SMALL_NETHER_DUNGEON.equals(id) || BASTION_OTHER.equals(id)) {
                    addEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.FIRE_PROTECTION)
                            .add(Enchantments.FLAME)
                            .add(Enchantments.FIRE_ASPECT)
                    );
                    add1in2ChanceExperienceBottle(tableBuilder);
                }
                if (NETHER_FORTRESS_KEEP.equals(id)) {
                    add1in4ChanceEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.FIRE_PROTECTION)
                            .add(Enchantments.FLAME)
                            .add(Enchantments.FIRE_ASPECT)
                    );
                    add1in2ChanceExperienceBottle(tableBuilder);
                }
                if (NETHER_FORTRESS_BEACON.equals(id)) {
                    addEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.POWER)
                    );
                    addExperienceBottle(tableBuilder, 1);
                }
            }
        });
    }

    private static void addRandomEnchantedBook(LootTable.Builder tableBuilder) {
        LootPool.Builder enchantedBookPoolBuilder = LootPool.builder()
                .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.builder()));
        tableBuilder.pool(enchantedBookPoolBuilder);
    }

    private static void addEnchantedBookFromList(LootTable.Builder tableBuilder, EnchantRandomlyLootFunction.Builder function) {
        LootPool.Builder enchantedBookPoolBuilder = LootPool.builder()
                .with(ItemEntry.builder(Items.BOOK).apply(function));
        tableBuilder.pool(enchantedBookPoolBuilder);
    }

    private static void add1in2ChanceEnchantedBookFromList(LootTable.Builder tableBuilder, EnchantRandomlyLootFunction.Builder function) {
        LootPool.Builder enchantedBookPoolBuilder = LootPool.builder()
                .with(ItemEntry.builder(Items.BOOK).apply(function).weight(50))
                .with(EmptyEntry.builder().weight(50));
        tableBuilder.pool(enchantedBookPoolBuilder);
    }

    private static void add1in4ChanceEnchantedBookFromList(LootTable.Builder tableBuilder, EnchantRandomlyLootFunction.Builder function) {
        LootPool.Builder enchantedBookPoolBuilder = LootPool.builder()
                .with(ItemEntry.builder(Items.BOOK).apply(function).weight(25))
                .with(EmptyEntry.builder().weight(75));
        tableBuilder.pool(enchantedBookPoolBuilder);
    }

    private static void addExperienceBottle(LootTable.Builder tableBuilder, float count) {
        LootPool.Builder bottleOfEnchantingPoolBuilder = LootPool.builder()
                .with(ItemEntry.builder(Items.EXPERIENCE_BOTTLE).apply(
                        SetCountLootFunction.builder(ConstantLootNumberProvider.create(count))));
        tableBuilder.pool(bottleOfEnchantingPoolBuilder);
    }

    private static void add1in2ChanceExperienceBottle(LootTable.Builder tableBuilder) {
        LootPool.Builder bottleOfEnchantingPoolBuilder = LootPool.builder()
                .with(ItemEntry.builder(Items.EXPERIENCE_BOTTLE).weight(50))
                .with(EmptyEntry.builder().weight(50));
        tableBuilder.pool(bottleOfEnchantingPoolBuilder);
    }

    private static void addRandomMusicDisc(LootTable.Builder tableBuilder) {
        LootPool.Builder musicDiscPoolBuilder = LootPool.builder()
                .with(ItemEntry.builder(Items.MUSIC_DISC_11).weight(1))
                .with(ItemEntry.builder(Items.MUSIC_DISC_13).weight(1))
                .with(ItemEntry.builder(Items.MUSIC_DISC_CAT).weight(1))
                .with(ItemEntry.builder(Items.MUSIC_DISC_BLOCKS).weight(1))
                .with(ItemEntry.builder(Items.MUSIC_DISC_CHIRP).weight(1))
                .with(ItemEntry.builder(Items.MUSIC_DISC_FAR).weight(1))
                .with(ItemEntry.builder(Items.MUSIC_DISC_MALL).weight(1))
                .with(ItemEntry.builder(Items.MUSIC_DISC_MELLOHI).weight(1))
                .with(ItemEntry.builder(Items.MUSIC_DISC_STAL).weight(1))
                .with(ItemEntry.builder(Items.MUSIC_DISC_STRAD).weight(1))
                .with(ItemEntry.builder(Items.MUSIC_DISC_WAIT).weight(1))
                .with(ItemEntry.builder(Items.MUSIC_DISC_WARD).weight(1))
                .with(EmptyEntry.builder().weight(108));
        tableBuilder.pool(musicDiscPoolBuilder);
    }
}
