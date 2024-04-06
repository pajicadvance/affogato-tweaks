package me.pajic.affogatotweaks.loot;

import me.pajic.affogatotweaks.util.LootUtil;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import static me.pajic.affogatotweaks.loot.LootTableIdentifiers.*;

public class LootTableModifier {
    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin()) {
                if (WOODLAND_MANSION.equals(id) || STRONGHOLD_LIBRARY.equals(id) || JUNGLE_TEMPLE.equals(id)
                        || DESERT_PYRAMID.equals(id) || ABANDONED_MINESHAFT.equals(id) || SIMPLE_DUNGEON.equals(id)
                        || STRONGHOLD_CORRIDOR.equals(id) || STRONGHOLD_CROSSING.equals(id) || ANCIENT_CITY.equals(id)) {
                    LootUtil.addRandomEnchantedBook(tableBuilder);
                    LootUtil.addExperienceBottle(tableBuilder, 1);
                    LootUtil.add1in10ChanceRandomMusicDisc(tableBuilder);
                }
                if (WOODLAND_MANSION.equals(id)) {
                    LootUtil.addTotemOfUndying(tableBuilder);
                }
                if (JUNGLE_TEMPLE_TREASURE.equals(id)) {
                    LootUtil.addRandomEnchantedBook(tableBuilder);
                    LootUtil.addRandomEnchantedBook(tableBuilder);
                    LootUtil.addExperienceBottle(tableBuilder, 3);
                    LootUtil.add1in10ChanceRandomMusicDisc(tableBuilder);
                    LootUtil.addTotemOfUndying(tableBuilder);
                }
                if (CAT_GIFT.equals(id)) {
                    LootUtil.add1in10ChanceRandomMusicDisc(tableBuilder);
                }
                if (DESERT_TEMPLE_LIBRARY.equals(id)) {
                    LootUtil.addEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.BINDING_CURSE)
                            .add(Enchantments.VANISHING_CURSE)
                    );
                }
                if (ANCIENT_CITY_ICE_BOX.equals(id)) {
                    LootUtil.addEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.FROST_WALKER)
                    );
                }
                if (EGG_ROOM.equals(id)) {
                    LootUtil.addEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.BANE_OF_ARTHROPODS)
                    );
                    LootUtil.addExperienceBottle(tableBuilder, 1);
                }
                if (BIG_UNDERWATER_RUIN.equals(id)) {
                    LootUtil.addEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.DEPTH_STRIDER)
                    );
                    LootUtil.addExperienceBottle(tableBuilder, 1);
                }
                if (ZOMBIE_DUNGEON_SPECIAL.equals(id) || ZOMBIE_DUNGEON_TOMBSTONE.equals(id)) {
                    LootUtil.addEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.SMITE)
                    );
                    LootUtil.addExperienceBottle(tableBuilder, 1);
                }
                if (NETHER_FORTRESS_BEACON.equals(id)) {
                    LootUtil.addEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.POWER)
                    );
                    LootUtil.addExperienceBottle(tableBuilder, 1);
                }
                if (SMALL_UNDERWATER_RUIN.equals(id)) {
                    LootUtil.add1in2ChanceEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.LURE)
                            .add(Enchantments.LUCK_OF_THE_SEA)
                    );
                    LootUtil.add1in2ChanceExperienceBottle(tableBuilder);
                }
                if (SKELETON_DUNGEON_COMMON.equals(id) || ZOMBIE_DUNGEON_COMMON.equals(id) || SKELETON_DUNGEON_MIDDLE.equals(id)) {
                    LootUtil.add1in2ChanceEnchantedBookFromList(tableBuilder, EnchantRandomlyLootFunction.create()
                            .add(Enchantments.SMITE)
                    );
                    LootUtil.add1in2ChanceExperienceBottle(tableBuilder);
                }
                if (END_CITY.equals(id)) {
                    LootPool.Builder enchantedBookPoolBuilder = LootPool.builder()
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                                    .add(Registries.ENCHANTMENT.get(new Identifier("enchantedshulkers", "augment")))
                                    .add(Registries.ENCHANTMENT.get(new Identifier("aileron", "smokestack")))
                                    .add(Registries.ENCHANTMENT.get(new Identifier("aileron", "cloudskipper")))
                            ).weight(80))
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                                    .add(Registries.ENCHANTMENT.get(new Identifier("enchantedshulkers", "siphon")))
                                    .add(Registries.ENCHANTMENT.get(new Identifier("enchantedshulkers", "refill")))
                            ).weight(20));
                    tableBuilder.pool(enchantedBookPoolBuilder);
                    LootUtil.addExperienceBottle(tableBuilder, 2);
                    LootUtil.add1in8ChanceTotemOfUndying(tableBuilder);
                    LootUtil.add1in10ChanceEnchantmentUpgradeSmithingTemplate(tableBuilder);
                }
                if (PILLAGER_OUTPOST.equals(id)) {
                    LootPool.Builder enchantedBookPoolBuilder = LootPool.builder()
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                                    .add(Enchantments.PIERCING)
                                    .add(Enchantments.QUICK_CHARGE)
                            ).weight(90))
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                                    .add(Enchantments.MULTISHOT)
                            ).weight(10));
                    tableBuilder.pool(enchantedBookPoolBuilder);
                    tableBuilder.pool(enchantedBookPoolBuilder);
                    LootUtil.addExperienceBottle(tableBuilder, 2);
                    LootUtil.addTotemOfUndying(tableBuilder);
                }
                if (ELDER_GUARDIAN.equals(id) || OCEAN_MONUMENT_UPPER_SIDE_CHAMBER.equals(id)) {
                    LootPool.Builder enchantedBookPoolBuilder = LootPool.builder()
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                                    .add(Enchantments.IMPALING)
                            ).weight(50))
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                                    .add(Enchantments.LOYALTY)
                                    .add(Enchantments.RIPTIDE)
                            ).weight(40))
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                                    .add(Enchantments.CHANNELING)
                            ).weight(10));
                    tableBuilder.pool(enchantedBookPoolBuilder);
                    LootUtil.addExperienceBottle(tableBuilder, 1);
                }
                if (BURIED_TREASURE.equals(id)) {
                    LootPool.Builder enchantedBookPoolBuilder = LootPool.builder()
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                                    .add(Enchantments.RESPIRATION)
                            ).weight(80))
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                                    .add(Enchantments.AQUA_AFFINITY)
                            ).weight(20));
                    tableBuilder.pool(enchantedBookPoolBuilder);
                    LootUtil.addExperienceBottle(tableBuilder, 2);
                }
                if (NETHER_BRIDGE.equals(id) || SMALL_NETHER_DUNGEON.equals(id) || BASTION_OTHER.equals(id)) {
                    LootPool.Builder enchantedBookPoolBuilder = LootPool.builder()
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                                    .add(Enchantments.FIRE_PROTECTION)
                            ).weight(80))
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                                    .add(Enchantments.FLAME)
                                    .add(Enchantments.FIRE_ASPECT)
                            ).weight(20));
                    tableBuilder.pool(enchantedBookPoolBuilder);
                    LootUtil.add1in2ChanceExperienceBottle(tableBuilder);
                }
                if (NETHER_FORTRESS_KEEP.equals(id)) {
                    LootPool.Builder enchantedBookPoolBuilder = LootPool.builder()
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                                    .add(Enchantments.FIRE_PROTECTION)
                            ).weight(20))
                            .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                                    .add(Enchantments.FLAME)
                                    .add(Enchantments.FIRE_ASPECT)
                            ).weight(5))
                            .with(EmptyEntry.builder().weight(75));
                    tableBuilder.pool(enchantedBookPoolBuilder);
                    LootUtil.add1in2ChanceExperienceBottle(tableBuilder);
                }
            }
        });
    }
}
