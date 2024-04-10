package me.pajic.affogatotweaks.util;

import me.pajic.affogatotweaks.item.ModItems;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class LootUtil {

    public static void addRandomEnchantedBook(LootTable.Builder tableBuilder) {
        LootPool.Builder enchantedBookPoolBuilder = LootPool.builder()
                .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                        .add(Enchantments.EFFICIENCY)
                        .add(Enchantments.SHARPNESS)
                        .add(Enchantments.PROTECTION)
                        .add(Enchantments.POWER)
                ).weight(55))
                .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                        .add(Enchantments.FEATHER_FALLING)
                        .add(Enchantments.PROJECTILE_PROTECTION)
                        .add(Enchantments.BLAST_PROTECTION)
                        .add(Enchantments.SWEEPING)
                        .add(Registries.ENCHANTMENT.get(new Identifier("guarding", "pummeling")))
                        .add(Registries.ENCHANTMENT.get(new Identifier("farmersdelight", "backstabbing")))
                ).weight(30))
                .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                        .add(Enchantments.KNOCKBACK)
                        .add(Enchantments.PUNCH)
                ).weight(10))
                .with(ItemEntry.builder(Items.BOOK).apply(EnchantRandomlyLootFunction.create()
                        .add(Enchantments.SILK_TOUCH)
                        .add(Registries.ENCHANTMENT.get(new Identifier("charm", "collection")))
                        .add(Registries.ENCHANTMENT.get(new Identifier("guarding", "barbed")))
                ).weight(5));
        tableBuilder.pool(enchantedBookPoolBuilder);
    }

    public static void addEnchantedBookFromList(LootTable.Builder tableBuilder, EnchantRandomlyLootFunction.Builder function) {
        LootPool.Builder enchantedBookPoolBuilder = LootPool.builder()
                .with(ItemEntry.builder(Items.BOOK).apply(function));
        tableBuilder.pool(enchantedBookPoolBuilder);
    }

    public static void add1in2ChanceEnchantedBookFromList(LootTable.Builder tableBuilder, EnchantRandomlyLootFunction.Builder function) {
        LootPool.Builder enchantedBookPoolBuilder = LootPool.builder()
                .with(ItemEntry.builder(Items.BOOK).apply(function).weight(50))
                .with(EmptyEntry.builder().weight(50));
        tableBuilder.pool(enchantedBookPoolBuilder);
    }

    public static void addExperienceBottle(LootTable.Builder tableBuilder, float count) {
        LootPool.Builder bottleOfEnchantingPoolBuilder = LootPool.builder()
                .with(ItemEntry.builder(Items.EXPERIENCE_BOTTLE).apply(
                        SetCountLootFunction.builder(ConstantLootNumberProvider.create(count))));
        tableBuilder.pool(bottleOfEnchantingPoolBuilder);
    }

    public static void add1in2ChanceExperienceBottle(LootTable.Builder tableBuilder) {
        LootPool.Builder bottleOfEnchantingPoolBuilder = LootPool.builder()
                .with(ItemEntry.builder(Items.EXPERIENCE_BOTTLE).weight(50))
                .with(EmptyEntry.builder().weight(50));
        tableBuilder.pool(bottleOfEnchantingPoolBuilder);
    }

    public static void addTotemOfUndying(LootTable.Builder tableBuilder) {
        LootPool.Builder bottleOfEnchantingPoolBuilder = LootPool.builder()
                .with(ItemEntry.builder(Items.TOTEM_OF_UNDYING));
        tableBuilder.pool(bottleOfEnchantingPoolBuilder);
    }

    public static void add1in8ChanceTotemOfUndying(LootTable.Builder tableBuilder) {
        LootPool.Builder bottleOfEnchantingPoolBuilder = LootPool.builder()
                .with(ItemEntry.builder(Items.TOTEM_OF_UNDYING).weight(10))
                .with(EmptyEntry.builder().weight(70));
        tableBuilder.pool(bottleOfEnchantingPoolBuilder);
    }

    public static void add1in10ChanceEnchantmentUpgradeSmithingTemplate(LootTable.Builder tableBuilder) {
        LootPool.Builder enchantmentUpgradePoolBuilder = LootPool.builder()
                .with(ItemEntry.builder(ModItems.ENCHANTMENT_UPGRADE_SMITHING_TEMPLATE).weight(10))
                .with(EmptyEntry.builder().weight(90));
        tableBuilder.pool(enchantmentUpgradePoolBuilder);
    }

    public static void add1in50ChanceColoredGlintSmithingTemplate(LootTable.Builder tableBuilder) {
        LootPool.Builder coloredGlintPoolBuilder = LootPool.builder()
                .with(ItemEntry.builder(Registries.ITEM.get(new Identifier("charm", "colored_glint_smithing_template"))).weight(2))
                .with(EmptyEntry.builder().weight(98));
        tableBuilder.pool(coloredGlintPoolBuilder);
    }

    public static void add1in10ChanceRandomMusicDisc(LootTable.Builder tableBuilder) {
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
