package me.pajic.affogato_core.config;

import me.fzzyhmstrs.fzzy_config.annotations.Version;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import me.fzzyhmstrs.fzzy_config.validation.collection.ValidatedList;
import me.fzzyhmstrs.fzzy_config.validation.collection.ValidatedMap;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedAny;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedBoolean;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedPair;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedString;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedDouble;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedFloat;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedNumber;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.util.*;
import net.minecraft.world.item.ToolMaterial;

import java.util.Map;

@SuppressWarnings({"unchecked", "rawtypes"})
@Version(version = 1)
public class ModConfig extends Config {
    public ModConfig() {
        super(Main.CONFIG_RL);
    }

    public Features features = new Features();
    public ExhaustionValues exhaustionValues = new ExhaustionValues();
    public Durabilities durabilities = new Durabilities();
    public Loot loot = new Loot();
    public Worldgen worldgen = new Worldgen();
    public Experience experience = new Experience();
    public Combat combat = new Combat();
    public ValidatedList<ToolStatReplacement> toolStats = new ValidatedAny<>(new ToolStatReplacement()).toList(
            new ToolStatReplacement(ToolMaterialId.of(ToolMaterial.WOOD), 80, 3.0F),
            new ToolStatReplacement(ToolMaterialId.of(ToolMaterial.STONE), 96, 4.0F),
            new ToolStatReplacement(ToolMaterialId.of(ToolMaterial.COPPER), 160, 5.0F),
            new ToolStatReplacement(ToolMaterialId.of(ToolMaterial.IRON), 320, 7.0F),
            new ToolStatReplacement(ToolMaterialId.of(ToolMaterial.GOLD), 80, 20.0F),
            new ToolStatReplacement(ToolMaterialId.of(ToolMaterial.DIAMOND), 1280, 9.0F),
            new ToolStatReplacement(ToolMaterialId.of(ToolMaterial.NETHERITE), 2560, 11.0F)
    );
    public ValidatedList<WeaponStatReplacement> weaponStats = new ValidatedAny<>(new WeaponStatReplacement()).toList(
            new WeaponStatReplacement(ToolMaterialId.of(ToolMaterial.WOOD), WeaponType.AXE, -3.4F),
            new WeaponStatReplacement(ToolMaterialId.of(ToolMaterial.STONE), WeaponType.AXE, -3.4F),
            new WeaponStatReplacement(ToolMaterialId.of(ToolMaterial.IRON), WeaponType.AXE, -3.4F),
            new WeaponStatReplacement(ToolMaterialId.of(ToolMaterial.COPPER), WeaponType.AXE, -3.4F),
            new WeaponStatReplacement(ToolMaterialId.of(ToolMaterial.DIAMOND), WeaponType.AXE, -3.4F),
            new WeaponStatReplacement(ToolMaterialId.of(ToolMaterial.NETHERITE), WeaponType.AXE, -3.4F),
            new WeaponStatReplacement(ToolMaterialId.of(ToolMaterial.WOOD), WeaponType.HOE, -2.0F),
            new WeaponStatReplacement(ToolMaterialId.of(ToolMaterial.STONE), WeaponType.HOE, -2.0F),
            new WeaponStatReplacement(ToolMaterialId.of(ToolMaterial.IRON), WeaponType.HOE, -2.0F),
            new WeaponStatReplacement(ToolMaterialId.of(ToolMaterial.COPPER), WeaponType.HOE, -2.0F),
            new WeaponStatReplacement(ToolMaterialId.of(ToolMaterial.DIAMOND), WeaponType.HOE, -2.0F),
            new WeaponStatReplacement(ToolMaterialId.of(ToolMaterial.NETHERITE), WeaponType.HOE, -2.0F),
            new WeaponStatReplacement(ToolMaterialId.of(ToolMaterial.GOLD), WeaponType.SWORD, -1.6F),
            new WeaponStatReplacement(ToolMaterialId.of(ToolMaterial.GOLD), WeaponType.PICKAXE, -2.2F),
            new WeaponStatReplacement(ToolMaterialId.of(ToolMaterial.GOLD), WeaponType.SHOVEL, -2.5F),
            new WeaponStatReplacement(ToolMaterialId.of(ToolMaterial.GOLD), WeaponType.HOE, 0.0F)
    );
    public ValidatedMap<String, Integer> furnaceFuelMap = (new ValidatedMap.Builder())
            .keyHandler(new ValidatedString())
            .valueHandler(new ValidatedInt(1, Integer.MAX_VALUE, 0))
            .defaults(Map.of(
                    "minecraft:blaze_rod", 16,
                    "minecraft:dried_kelp_block", 0,
                    "#minecraft:wool_carpets", 0,
                    "#minecraft:wool", 0
            )).build();
    public ValidatedList<String> removedRecipes = new ValidatedString("").toList(
            "storagedrawers:recipe/obsidian_storage_upgrade",
            "storagedrawers:recipe/copper_storage_upgrade",
            "storagedrawers:recipe/iron_storage_upgrade",
            "storagedrawers:recipe/gold_storage_upgrade",
            "storagedrawers:recipe/diamond_storage_upgrade",
            "storagedrawers:recipe/emerald_storage_upgrade",
            "storagedrawers:recipe/netherite_storage_upgrade",
            "storagedrawers:recipe/void_upgrade",
            "storagedrawers:recipe/conversion_upgrade",
            "storagedrawers:recipe/illumination_upgrade",
            "storagedrawers:recipe/portability_upgrade",
            "farmersdelight:recipe/cake_from_milk_bottle",
            "farmersdelight:recipe/cutting/gravel",
            "friendsandfoes:recipe/wildfire_crown",
            "friendsandfoes:recipe/waxed_lightning_rod"
    );
    public ValidatedList<String> hiddenItems = new ValidatedString("").toList(
            "storagedrawers:obsidian_storage_upgrade",
            "storagedrawers:copper_storage_upgrade",
            "storagedrawers:iron_storage_upgrade",
            "storagedrawers:gold_storage_upgrade",
            "storagedrawers:diamond_storage_upgrade",
            "storagedrawers:emerald_storage_upgrade",
            "storagedrawers:netherite_storage_upgrade",
            "storagedrawers:void_upgrade",
            "storagedrawers:conversion_upgrade",
            "storagedrawers:illumination_upgrade",
            "storagedrawers:portability_upgrade",
            "friendsandfoes:wildfire_crown",
            "friendsandfoes:wildfire_crown_fragment",
            "friendsandfoes:waxed_lightning_rod",
            "friendsandfoes:waxed_exposed_lightning_rod",
            "friendsandfoes:waxed_weathered_lightning_rod",
            "friendsandfoes:waxed_oxidized_lightning_rod",
            "friendsandfoes:exposed_lightning_rod",
            "friendsandfoes:weathered_lightning_rod",
            "friendsandfoes:oxidized_lightning_rod"
    );
    public Misc misc = new Misc();
    public Debug debug = new Debug();

    public static class Features extends ConfigSection {
        public ValidatedBoolean villagerNuke = new ValidatedBoolean();
        public ValidatedBoolean allowWanderingTraderTrading = new ValidatedBoolean();
        public ValidatedBoolean raidRework = new ValidatedBoolean();
        public ValidatedBoolean nightVisionNuke = new ValidatedBoolean();
        public ValidatedBoolean hardDifficultyDefault = new ValidatedBoolean();
        public ValidatedBoolean alwaysUpdateMaps = new ValidatedBoolean();
        public ValidatedBoolean noRiptideInRain = new ValidatedBoolean();
        public ValidatedBoolean affogatoEarlyGameChanges = new ValidatedBoolean();
        public ValidatedBoolean affogatoBlockLootTableEdits = new ValidatedBoolean();
        public ValidatedBoolean affogatoEntityLootTableEdits = new ValidatedBoolean();
        public ValidatedBoolean affogatoRecipeEdits = new ValidatedBoolean();
        public ValidatedBoolean affogatoWanderingTraderTrades = new ValidatedBoolean();
        public ValidatedBoolean affogatoItemSwapperAddon = new ValidatedBoolean();
        public ValidatedBoolean affogatoFDAddon = new ValidatedBoolean();
    }

    public static class ExhaustionValues extends ConfigSection {
        public ValidatedFloat attack = new ValidatedFloat(0.5F);
        public ValidatedFloat blockAttack = new ValidatedFloat(0.1F);
        public ValidatedFloat breakBlock = new ValidatedFloat(0.05F);
        public ValidatedFloat climb = new ValidatedFloat(0.01F);
        public ValidatedFloat crawl = new ValidatedFloat(0.04F);
        public ValidatedFloat drawRangedWeapon = new ValidatedFloat(0.25F);
        public ValidatedFloat hungerEffectTick = new ValidatedFloat(0.05F);
        public ValidatedFloat jump = new ValidatedFloat(0.025F);
        public ValidatedFloat jumpSprint = new ValidatedFloat(0.8F);
        public ValidatedFloat naturalHealing = new ValidatedFloat(8.0F);
        public ValidatedFloat sneak = new ValidatedFloat(0.02F);
        public ValidatedFloat sprint = new ValidatedFloat(0.02F);
        public ValidatedFloat swimFast = new ValidatedFloat(0.02F);
        public ValidatedFloat swimSlow = new ValidatedFloat(0.0F);
        public ValidatedFloat takeDamage = new ValidatedFloat(2.0F);
        public ValidatedFloat throwWeapon = new ValidatedFloat(0.25F);
    }

    public static class Durabilities extends ConfigSection {
        public ValidatedInt helmetBase = new ValidatedInt(13);
        public ValidatedInt bodyBase = new ValidatedInt(16);
        public ValidatedInt leggingsBase = new ValidatedInt(15);
        public ValidatedInt bootsBase = new ValidatedInt(12);
        public ValidatedInt leatherArmorMult = new ValidatedInt(8);
        public ValidatedInt copperArmorMult = new ValidatedInt(10);
        public ValidatedInt ironArmorMult = new ValidatedInt(12);
        public ValidatedInt goldenArmorMult = new ValidatedInt(6);
        public ValidatedInt diamondArmorMult = new ValidatedInt(20);
        public ValidatedInt netheriteArmorMult = new ValidatedInt(30);
        public ValidatedInt turtleArmorMult = new ValidatedInt(16);
        public ValidatedInt wolfArmorMult = new ValidatedInt(8);
        public ValidatedInt bow = new ValidatedInt(256);
        public ValidatedInt elytra = new ValidatedInt(640);
        public ValidatedInt shears = new ValidatedInt(256);
        public ValidatedInt shield = new ValidatedInt(384);
        public ValidatedInt crossbow = new ValidatedInt(320);
        public ValidatedInt trident = new ValidatedInt(384);
    }

    public static class Loot extends ConfigSection {
        public ValidatedMap<String, LootEntryReplacement> lootEntryReplacements = (new ValidatedMap.Builder())
                .keyHandler(new ValidatedString())
                .valueHandler(new ValidatedAny<>(new LootEntryReplacement()))
                .defaults(Map.ofEntries(
                        Map.entry("minecraft:diamond_helmet", new LootEntryReplacement("minecraft:diamond", 5)),
                        Map.entry("minecraft:diamond_chestplate", new LootEntryReplacement("minecraft:diamond", 8)),
                        Map.entry("minecraft:diamond_leggings", new LootEntryReplacement("minecraft:diamond", 7)),
                        Map.entry("minecraft:diamond_boots", new LootEntryReplacement("minecraft:diamond", 4)),
                        Map.entry("minecraft:diamond_pickaxe", new LootEntryReplacement("minecraft:diamond", 3)),
                        Map.entry("minecraft:diamond_axe", new LootEntryReplacement("minecraft:diamond", 3)),
                        Map.entry("minecraft:diamond_shovel", new LootEntryReplacement("minecraft:diamond", 1)),
                        Map.entry("minecraft:diamond_sword", new LootEntryReplacement("minecraft:diamond", 2)),
                        Map.entry("minecraft:diamond_hoe", new LootEntryReplacement("minecraft:diamond", 2)),
                        Map.entry("minecraft:diamond_spear", new LootEntryReplacement("minecraft:diamond", 1)),
                        Map.entry("minecraft:iron_helmet", new LootEntryReplacement("minecraft:iron_ingot", 5)),
                        Map.entry("minecraft:iron_chestplate", new LootEntryReplacement("minecraft:iron_ingot", 8)),
                        Map.entry("minecraft:iron_leggings", new LootEntryReplacement("minecraft:iron_ingot", 7)),
                        Map.entry("minecraft:iron_boots", new LootEntryReplacement("minecraft:iron_ingot", 4)),
                        Map.entry("minecraft:iron_pickaxe", new LootEntryReplacement("minecraft:iron_ingot", 3)),
                        Map.entry("minecraft:iron_axe", new LootEntryReplacement("minecraft:iron_ingot", 3)),
                        Map.entry("minecraft:iron_shovel", new LootEntryReplacement("minecraft:iron_ingot", 1)),
                        Map.entry("minecraft:iron_sword", new LootEntryReplacement("minecraft:iron_ingot", 2)),
                        Map.entry("minecraft:iron_hoe", new LootEntryReplacement("minecraft:iron_ingot", 2)),
                        Map.entry("minecraft:iron_spear", new LootEntryReplacement("minecraft:iron_ingot", 1)),
                        Map.entry("farmersdelight:diamond_knife", new LootEntryReplacement("minecraft:diamond", 1)),
                        Map.entry("farmersdelight:iron_knife", new LootEntryReplacement("minecraft:iron_ingot", 1)),
                        Map.entry("minecraft:stone_pickaxe", new LootEntryReplacement("minecraft:cobblestone", 3)),
                        Map.entry("minecraft:stone_shovel", new LootEntryReplacement("minecraft:cobblestone", 1)),
                        Map.entry("minecraft:stone_sword", new LootEntryReplacement("minecraft:cobblestone", 2)),
                        Map.entry("minecraft:stone_axe", new LootEntryReplacement("minecraft:cobblestone", 3)),
                        Map.entry("minecraft:stone_hoe", new LootEntryReplacement("minecraft:cobblestone", 2)),
                        Map.entry("minecraft:stone_spear", new LootEntryReplacement("minecraft:cobblestone", 1))
                )).build();
        public ValidatedMap<String, Double> totemMap = (new ValidatedMap.Builder())
                .keyHandler(new ValidatedString())
                .valueHandler(new ValidatedDouble(0.0, 1.0, 0.0))
                .defaults(Map.of(
                        "jungle_temple", 0.5,
                        "pillager_outpost", 0.75,
                        "woodland_mansion", 1.0
                )).build();
        public ValidatedDouble trialExplorerMapChance = new ValidatedDouble(0.08, 1, 0);
        public ValidatedDouble woodlandExplorerMapChance = new ValidatedDouble(0.33, 1, 0);
        public ValidatedDouble oceanExplorerMapChance = new ValidatedDouble(0.5, 1, 0);
        public ValidatedDouble curseEnchantedBookChance = new ValidatedDouble(0.5, 1, 0);
        public ValidatedDouble frostWalkerIglooChance = new ValidatedDouble(0.5, 1, 0);
        public ValidatedDouble frostWalkerIceBoxChance = new ValidatedDouble(1, 1, 0);
        public ValidatedDouble globeBannerPatternChance = new ValidatedDouble(1, 1, 0);
        public ValidatedBoolean maceEnchantmentsInOminousVaults = new ValidatedBoolean();
        public ValidatedBoolean tridentEnchantmentsFromElderGuardians = new ValidatedBoolean();
        public ValidatedBoolean frostWalkerOnlyInIgloosAndIceBox = new ValidatedBoolean();
        public ValidatedBoolean cursesOnlyInDesertPyramids = new ValidatedBoolean();
    }

    public static class Worldgen extends ConfigSection {
        public ValidatedFloat dungeonChanceMult = new ValidatedFloat(1.3F);
        public ValidatedFloat mineshaftChanceMult = new ValidatedFloat(1.2F);
        public ValidatedFloat oreSizeMult = new ValidatedFloat(1.2F);
        public ValidatedInt ancientDebrisSize = new ValidatedInt(6);
        public ValidatedInt ancientDebrisMinAmount = new ValidatedInt(3);
        public ValidatedBoolean removeAncientDebrisHeightLimit = new ValidatedBoolean();
        public ValidatedBoolean addRandomChestsToMansions = new ValidatedBoolean();
        public ValidatedFloat mansionRandomChestChance = new ValidatedFloat(0.008F, 1.0F, 0F, ValidatedNumber.WidgetType.TEXTBOX);
        public ValidatedBoolean addRandomChestsToMonuments = new ValidatedBoolean();
        public ValidatedFloat monumentRandomChestChance = new ValidatedFloat(0.004F, 1.0F, 0F, ValidatedNumber.WidgetType.TEXTBOX);
        public ValidatedBoolean cliffFixes = new ValidatedBoolean();
    }

    public static class Experience extends ConfigSection {
        public ValidatedMap<String, Double> recipeXpSwapByResult = (new ValidatedMap.Builder())
                .keyHandler(new ValidatedString())
                .valueHandler(new ValidatedDouble(0))
                .defaults(Map.of(
                        "minecraft:iron_ingot", 1.5,
                        "minecraft:copper_ingot", 1.0,
                        "minecraft:netherite_scrap", 10.0,
                        "minecraft:iron_nugget", 1.0,
                        "minecraft:gold_nugget", 1.0,
                        "minecraft:copper_nugget", 1.0
                )).build();
        public ValidatedMap<String, Double> recipeXpSwapByInput = (new ValidatedMap.Builder())
                .keyHandler(new ValidatedString())
                .valueHandler(new ValidatedDouble(0))
                .defaults(Map.of(
                        "minecraft:raw_gold", 3.0,
                        "minecraft:gold_ore", 3.0,
                        "minecraft:deepslate_gold_ore", 3.0,
                        "minecraft:nether_gold_ore", 0.75
                )).build();
        public ValidatedPair<Integer, Integer> coal = new ValidatedInt(0).pairWith(new ValidatedInt(3));
        public ValidatedPair<Integer, Integer> netherGold = new ValidatedInt(0).pairWith(new ValidatedInt(3));
        public ValidatedPair<Integer, Integer> lapis = new ValidatedInt(3).pairWith(new ValidatedInt(7));
        public ValidatedPair<Integer, Integer> diamond = new ValidatedInt(7).pairWith(new ValidatedInt(10));
        public ValidatedPair<Integer, Integer> emerald = new ValidatedInt(12).pairWith(new ValidatedInt(15));
        public ValidatedPair<Integer, Integer> redstone = new ValidatedInt(3).pairWith(new ValidatedInt(7));
        public ValidatedPair<Integer, Integer> quartz = new ValidatedInt(3).pairWith(new ValidatedInt(7));
        public ValidatedBoolean noEnderDragonXpAfterFirst = new ValidatedBoolean();
        public ValidatedBoolean noFishingXp = new ValidatedBoolean();
        public ValidatedBoolean noMobXpDrops = new ValidatedBoolean();
    }

    public static class Combat extends ConfigSection {
        public ValidatedFloat eatTimeIncrease = new ValidatedFloat(1.6F);
        public ValidatedFloat shieldBlockDamageThreshold = new ValidatedFloat(1.0F);
        public ValidatedInt blazeFollowRange = new ValidatedInt(32);
        public ValidatedInt illusionerFollowRange = new ValidatedInt(16);
        public ValidatedInt pillagerFollowRange = new ValidatedInt(24);
        public ValidatedInt ravagerFollowRange = new ValidatedInt(16);
        public ValidatedInt zombieFollowRange = new ValidatedInt(16);
        public ValidatedInt zombieAlertOtherRange = new ValidatedInt(35);
        public ValidatedInt drownedTridentThrowDelay = new ValidatedInt(80);
        public ValidatedInt endermanAttackDelay = new ValidatedInt(40);
    }

    public static class Misc extends ConfigSection {
        public ValidatedBoolean noRapidHealing = new ValidatedBoolean();
        public ValidatedInt cakeSliceFoodLevel = new ValidatedInt(3);
        public ValidatedFloat cakeSliceSaturationLevel = new ValidatedFloat(0.3F);
        public ValidatedInt torchLightLevel = new ValidatedInt(12);
        public ValidatedInt copperTorchLightLevel = new ValidatedInt(12);
        public ValidatedFloat iceFriction = new ValidatedFloat(0.965F);
        public ValidatedFloat blueIceFriction = new ValidatedFloat(0.97F);
        public ValidatedBoolean hoglinSpawnOnlyOnCrimsonNylium = new ValidatedBoolean();
        public ValidatedBoolean frostWalkerEarlyTrigger = new ValidatedBoolean();
        public ValidatedBoolean ironMinesObsidian = new ValidatedBoolean();
        public ValidatedBoolean moreStoneTypesInStoneCraftingRecipes = new ValidatedBoolean();
        public ValidatedBoolean nullscapeEndAmbienceEdits = new ValidatedBoolean();
        public ValidatedInt caveAmbientSoundFrequency = new ValidatedInt(1200, Integer.MAX_VALUE, 1);
        public ValidatedInt coffinActivationRange = new ValidatedInt(6, 48, 1);
        public ValidatedInt reliableGliderDurability = new ValidatedInt(240);
        public ValidatedBoolean repairableReliableGlider = new ValidatedBoolean();
    }

    public static class Debug extends ConfigSection {
        public ValidatedBoolean logMaterialIds = new ValidatedBoolean(false);
        public ValidatedBoolean logDebugMessages = new ValidatedBoolean(false);
        public ValidatedBoolean exportJsonPatches = new ValidatedBoolean(false);
    }
}