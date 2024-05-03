package me.pajic.affogatotweaks.loot;

import me.pajic.affogatotweaks.util.LootUtil;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;

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
                    LootUtil.add1in50ChanceColoredGlintSmithingTemplate(tableBuilder);
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
                    LootUtil.addEnchantedBookFromList(tableBuilder, EnchantRandomlyFunction.randomEnchantment()
                            .withEnchantment(Enchantments.BINDING_CURSE)
                            .withEnchantment(Enchantments.VANISHING_CURSE)
                    );
                }
                if (ANCIENT_CITY_ICE_BOX.equals(id)) {
                    LootUtil.addEnchantedBookFromList(tableBuilder, EnchantRandomlyFunction.randomEnchantment()
                            .withEnchantment(Enchantments.FROST_WALKER)
                    );
                }
                if (EGG_ROOM.equals(id)) {
                    LootUtil.addEnchantedBookFromList(tableBuilder, EnchantRandomlyFunction.randomEnchantment()
                            .withEnchantment(Enchantments.BANE_OF_ARTHROPODS)
                    );
                    LootUtil.addExperienceBottle(tableBuilder, 1);
                }
                if (BIG_UNDERWATER_RUIN.equals(id)) {
                    LootUtil.addEnchantedBookFromList(tableBuilder, EnchantRandomlyFunction.randomEnchantment()
                            .withEnchantment(Enchantments.DEPTH_STRIDER)
                    );
                    LootUtil.addExperienceBottle(tableBuilder, 1);
                }
                if (ZOMBIE_DUNGEON_SPECIAL.equals(id) || ZOMBIE_DUNGEON_TOMBSTONE.equals(id)) {
                    LootUtil.addEnchantedBookFromList(tableBuilder, EnchantRandomlyFunction.randomEnchantment()
                            .withEnchantment(Enchantments.SMITE)
                    );
                    LootUtil.addExperienceBottle(tableBuilder, 1);
                }
                if (NETHER_FORTRESS_BEACON.equals(id)) {
                    LootUtil.addEnchantedBookFromList(tableBuilder, EnchantRandomlyFunction.randomEnchantment()
                            .withEnchantment(Enchantments.POWER_ARROWS)
                    );
                    LootUtil.addExperienceBottle(tableBuilder, 1);
                }
                if (SMALL_UNDERWATER_RUIN.equals(id)) {
                    LootUtil.add1in2ChanceEnchantedBookFromList(tableBuilder, EnchantRandomlyFunction.randomEnchantment()
                            .withEnchantment(Enchantments.FISHING_SPEED)
                            .withEnchantment(Enchantments.FISHING_LUCK)
                    );
                    LootUtil.add1in2ChanceExperienceBottle(tableBuilder);
                }
                if (SKELETON_DUNGEON_COMMON.equals(id) || ZOMBIE_DUNGEON_COMMON.equals(id) || SKELETON_DUNGEON_MIDDLE.equals(id)) {
                    LootUtil.add1in2ChanceEnchantedBookFromList(tableBuilder, EnchantRandomlyFunction.randomEnchantment()
                            .withEnchantment(Enchantments.SMITE)
                    );
                    LootUtil.add1in2ChanceExperienceBottle(tableBuilder);
                }
                if (END_CITY.equals(id)) {
                    LootPool.Builder enchantedBookPoolBuilder = LootPool.lootPool()
                            .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                                    .withEnchantment(BuiltInRegistries.ENCHANTMENT.get(new ResourceLocation("aileron", "smokestack")))
                                    .withEnchantment(BuiltInRegistries.ENCHANTMENT.get(new ResourceLocation("aileron", "cloudskipper")))
                            ).setWeight(80))
                            .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                                    .withEnchantment(BuiltInRegistries.ENCHANTMENT.get(new ResourceLocation("enchantedshulkers", "siphon")))
                                    .withEnchantment(BuiltInRegistries.ENCHANTMENT.get(new ResourceLocation("enchantedshulkers", "refill")))
                            ).setWeight(20));
                    tableBuilder.withPool(enchantedBookPoolBuilder);
                    LootUtil.addExperienceBottle(tableBuilder, 2);
                    LootUtil.add1in8ChanceTotemOfUndying(tableBuilder);
                    LootUtil.add1in10ChanceEnchantmentUpgradeSmithingTemplate(tableBuilder);
                    LootUtil.add1in50ChanceColoredGlintSmithingTemplate(tableBuilder);
                }
                if (PILLAGER_OUTPOST.equals(id)) {
                    LootPool.Builder enchantedBookPoolBuilder = LootPool.lootPool()
                            .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                                    .withEnchantment(Enchantments.PIERCING)
                                    .withEnchantment(Enchantments.QUICK_CHARGE)
                            ).setWeight(90))
                            .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                                    .withEnchantment(Enchantments.MULTISHOT)
                            ).setWeight(10));
                    tableBuilder.withPool(enchantedBookPoolBuilder);
                    tableBuilder.withPool(enchantedBookPoolBuilder);
                    LootUtil.addExperienceBottle(tableBuilder, 2);
                    LootUtil.addTotemOfUndying(tableBuilder);
                }
                if (ELDER_GUARDIAN.equals(id) || OCEAN_MONUMENT_UPPER_SIDE_CHAMBER.equals(id)) {
                    LootPool.Builder enchantedBookPoolBuilder = LootPool.lootPool()
                            .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                                    .withEnchantment(Enchantments.IMPALING)
                            ).setWeight(50))
                            .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                                    .withEnchantment(Enchantments.LOYALTY)
                                    .withEnchantment(Enchantments.RIPTIDE)
                            ).setWeight(40))
                            .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                                    .withEnchantment(Enchantments.CHANNELING)
                            ).setWeight(10));
                    tableBuilder.withPool(enchantedBookPoolBuilder);
                    LootUtil.addExperienceBottle(tableBuilder, 1);
                }
                if (BURIED_TREASURE.equals(id)) {
                    LootPool.Builder enchantedBookPoolBuilder = LootPool.lootPool()
                            .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                                    .withEnchantment(Enchantments.RESPIRATION)
                            ).setWeight(80))
                            .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                                    .withEnchantment(Enchantments.AQUA_AFFINITY)
                            ).setWeight(20));
                    tableBuilder.withPool(enchantedBookPoolBuilder);
                    LootUtil.addExperienceBottle(tableBuilder, 2);
                }
                if (NETHER_BRIDGE.equals(id) || SMALL_NETHER_DUNGEON.equals(id) || BASTION_OTHER.equals(id)) {
                    LootPool.Builder enchantedBookPoolBuilder = LootPool.lootPool()
                            .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                                    .withEnchantment(Enchantments.FIRE_PROTECTION)
                            ).setWeight(80))
                            .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                                    .withEnchantment(Enchantments.FLAMING_ARROWS)
                                    .withEnchantment(Enchantments.FIRE_ASPECT)
                            ).setWeight(20));
                    tableBuilder.withPool(enchantedBookPoolBuilder);
                    LootUtil.add1in2ChanceExperienceBottle(tableBuilder);
                }
                if (NETHER_FORTRESS_KEEP.equals(id)) {
                    LootPool.Builder enchantedBookPoolBuilder = LootPool.lootPool()
                            .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                                    .withEnchantment(Enchantments.FIRE_PROTECTION)
                            ).setWeight(20))
                            .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                                    .withEnchantment(Enchantments.FLAMING_ARROWS)
                                    .withEnchantment(Enchantments.FIRE_ASPECT)
                            ).setWeight(5))
                            .add(EmptyLootItem.emptyItem().setWeight(75));
                    tableBuilder.withPool(enchantedBookPoolBuilder);
                    LootUtil.add1in2ChanceExperienceBottle(tableBuilder);
                }
            }
        });
    }
}
