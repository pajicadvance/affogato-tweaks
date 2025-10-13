package me.pajic.affogatotweaks.values;

import java.util.Set;

public class MiscValues {
    public static final float EAT_TIME_INCREASE = 1.6F;
    public static final int TORCH_LIGHT_LEVEL = 12;
    public static final int COPPER_TORCH_LIGHT_LEVEL = 12;
    public static final int BLAZE_ROD_FUEL_TIME = 3200;
    public static final double SNEAK_RANGE_REDUCTION_MULT = 0.375;
    public static final float SHIELD_BLOCK_DAMAGE_THRESHOLD = 1.0F;
    public static final float ICE_FRICTION = 0.965F;
    public static final float BLUE_ICE_FRICTION = 0.97F;
    public static final int BACKPACK_SLOTS_PER_UPGRADE = 7;

    public static final Set<String> HIDDEN_ITEMS = Set.of(
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
            "inmis:baby_backpack",
            "inmis:endless_backpack",
            "inmis:withered_backpack",
            "inmis:ender_pouch",
            "friendsandfoes:wildfire_crown",
            "friendsandfoes:wildfire_crown_fragment",
            "friendsandfoes:waxed_lightning_rod",
            "friendsandfoes:waxed_exposed_lightning_rod",
            "friendsandfoes:waxed_weathered_lightning_rod",
            "friendsandfoes:waxed_oxidized_lightning_rod",
            "friendsandfoes:exposed_lightning_rod",
            "friendsandfoes:weathered_lightning_rod",
            "friendsandfoes:oxidized_lightning_rod",
            "minecraft:stone_pickaxe",
            "minecraft:stone_shovel",
            "minecraft:stone_sword",
            "minecraft:stone_axe",
            "minecraft:stone_hoe"
    );

    public static final Set<String> REMOVED_RECIPES = Set.of(
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
            "inmis:recipe/baby_backpack",
            "inmis:recipe/endless_backpack",
            "inmis:recipe/withered_backpack",
            "inmis:recipe/blazing_backpack",
            "inmis:recipe/ender_pouch",
            "minecraft:recipe/cake",
            "farmersdelight:recipe/cake_from_milk_bottle",
            "farmersdelight:recipe/cutting/gravel",
            "friendsandfoes:recipe/wildfire_crown",
            "friendsandfoes:recipe/waxed_lightning_rod",
            "minecraft:stone_pickaxe",
            "minecraft:stone_shovel",
            "minecraft:stone_sword",
            "minecraft:stone_axe",
            "minecraft:stone_hoe"
    );
}
