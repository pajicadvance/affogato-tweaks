package me.pajic.affogatotweaks.util;

import me.pajic.affogatotweaks.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class LootUtil {

    public static void addRandomEnchantedBook(LootTable.Builder tableBuilder) {
        LootPool.Builder enchantedBookPoolBuilder = LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                        .withEnchantment(Enchantments.BLOCK_EFFICIENCY)
                        .withEnchantment(Enchantments.SHARPNESS)
                        .withEnchantment(Enchantments.ALL_DAMAGE_PROTECTION)
                        .withEnchantment(Enchantments.POWER_ARROWS)
                ).setWeight(55))
                .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                        .withEnchantment(Enchantments.FALL_PROTECTION)
                        .withEnchantment(Enchantments.PROJECTILE_PROTECTION)
                        .withEnchantment(Enchantments.BLAST_PROTECTION)
                        .withEnchantment(Enchantments.SWEEPING_EDGE)
                        .withEnchantment(BuiltInRegistries.ENCHANTMENT.get(new ResourceLocation("guarding", "pummeling")))
                        .withEnchantment(BuiltInRegistries.ENCHANTMENT.get(new ResourceLocation("farmersdelight", "backstabbing")))
                ).setWeight(30))
                .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                        .withEnchantment(Enchantments.KNOCKBACK)
                        .withEnchantment(Enchantments.PUNCH_ARROWS)
                ).setWeight(10))
                .add(LootItem.lootTableItem(Items.BOOK).apply(EnchantRandomlyFunction.randomEnchantment()
                        .withEnchantment(Enchantments.SILK_TOUCH)
                        .withEnchantment(BuiltInRegistries.ENCHANTMENT.get(new ResourceLocation("charm", "collection")))
                        .withEnchantment(BuiltInRegistries.ENCHANTMENT.get(new ResourceLocation("guarding", "barbed")))
                ).setWeight(5));
        tableBuilder.withPool(enchantedBookPoolBuilder);
    }

    public static void addEnchantedBookFromList(LootTable.Builder tableBuilder, EnchantRandomlyFunction.Builder function) {
        LootPool.Builder enchantedBookPoolBuilder = LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.BOOK).apply(function));
        tableBuilder.withPool(enchantedBookPoolBuilder);
    }

    public static void add1in2ChanceEnchantedBookFromList(LootTable.Builder tableBuilder, EnchantRandomlyFunction.Builder function) {
        LootPool.Builder enchantedBookPoolBuilder = LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.BOOK).apply(function).setWeight(50))
                .add(EmptyLootItem.emptyItem().setWeight(50));
        tableBuilder.withPool(enchantedBookPoolBuilder);
    }

    public static void addExperienceBottle(LootTable.Builder tableBuilder, float count) {
        LootPool.Builder bottleOfEnchantingPoolBuilder = LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE).apply(
                        SetItemCountFunction.setCount(ConstantValue.exactly(count))));
        tableBuilder.withPool(bottleOfEnchantingPoolBuilder);
    }

    public static void add1in2ChanceExperienceBottle(LootTable.Builder tableBuilder) {
        LootPool.Builder bottleOfEnchantingPoolBuilder = LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE).setWeight(50))
                .add(EmptyLootItem.emptyItem().setWeight(50));
        tableBuilder.withPool(bottleOfEnchantingPoolBuilder);
    }

    public static void addTotemOfUndying(LootTable.Builder tableBuilder) {
        LootPool.Builder bottleOfEnchantingPoolBuilder = LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.TOTEM_OF_UNDYING));
        tableBuilder.withPool(bottleOfEnchantingPoolBuilder);
    }

    public static void add1in8ChanceTotemOfUndying(LootTable.Builder tableBuilder) {
        LootPool.Builder bottleOfEnchantingPoolBuilder = LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.TOTEM_OF_UNDYING).setWeight(10))
                .add(EmptyLootItem.emptyItem().setWeight(70));
        tableBuilder.withPool(bottleOfEnchantingPoolBuilder);
    }

    public static void add1in10ChanceEnchantmentUpgradeSmithingTemplate(LootTable.Builder tableBuilder) {
        LootPool.Builder enchantmentUpgradePoolBuilder = LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.ENCHANTMENT_UPGRADE_SMITHING_TEMPLATE).setWeight(10))
                .add(EmptyLootItem.emptyItem().setWeight(90));
        tableBuilder.withPool(enchantmentUpgradePoolBuilder);
    }

    public static void add1in50ChanceColoredGlintSmithingTemplate(LootTable.Builder tableBuilder) {
        LootPool.Builder coloredGlintPoolBuilder = LootPool.lootPool()
                .add(LootItem.lootTableItem(BuiltInRegistries.ITEM.get(new ResourceLocation("charm", "colored_glint_smithing_template"))).setWeight(2))
                .add(EmptyLootItem.emptyItem().setWeight(98));
        tableBuilder.withPool(coloredGlintPoolBuilder);
    }

    public static void add1in10ChanceRandomMusicDisc(LootTable.Builder tableBuilder) {
        LootPool.Builder musicDiscPoolBuilder = LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_11).setWeight(1))
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_13).setWeight(1))
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_CAT).setWeight(1))
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_BLOCKS).setWeight(1))
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_CHIRP).setWeight(1))
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_FAR).setWeight(1))
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_MALL).setWeight(1))
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_MELLOHI).setWeight(1))
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_STAL).setWeight(1))
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_STRAD).setWeight(1))
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_WAIT).setWeight(1))
                .add(LootItem.lootTableItem(Items.MUSIC_DISC_WARD).setWeight(1))
                .add(EmptyLootItem.emptyItem().setWeight(108));
        tableBuilder.withPool(musicDiscPoolBuilder);
    }
}
