package me.pajic.affogatotweaks.trade;

import net.minecraft.tags.StructureTags;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import org.apache.commons.lang3.tuple.Pair;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import vectorwing.farmersdelight.common.registry.ModItems;

public class WanderingTraderPools {
    public static final Pair<VillagerTrades.ItemListing[], Integer> conversionPool = Pair.of(
            new VillagerTrades.ItemListing[]{
                    new VillagerTrades.EmeraldForItems(Items.DIAMOND, 4, 16, 0, 1),
                    new VillagerTrades.EmeraldForItems(Items.GOLD_INGOT, 8, 8, 0, 1)
            },
            2
    );
    public static final Pair<VillagerTrades.ItemListing[], Integer> xpBottlePool = Pair.of(
            new VillagerTrades.ItemListing[]{
                    new VillagerTrades.ItemsForEmeralds(Items.EXPERIENCE_BOTTLE, 1, 4, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.EXPERIENCE_BOTTLE, 1, 5, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.EXPERIENCE_BOTTLE, 1, 6, 4, 0)
            },
            1
    );
    public static final Pair<VillagerTrades.ItemListing[], Integer> explorerMapPool = Pair.of(
            new VillagerTrades.ItemListing[]{
                    new VillagerTrades.TreasureMapForEmeralds(8, StructureTags.ON_JUNGLE_EXPLORER_MAPS, "filled_map.explorer_jungle", MapDecorationTypes.JUNGLE_TEMPLE, 1, 0),
                    new VillagerTrades.TreasureMapForEmeralds(8, StructureTags.ON_SWAMP_EXPLORER_MAPS, "filled_map.explorer_swamp", MapDecorationTypes.SWAMP_HUT, 1, 0)
            },
            1
    );
    public static final Pair<VillagerTrades.ItemListing[], Integer> dyePool = Pair.of(
            new VillagerTrades.ItemListing[]{
                    new VillagerTrades.ItemsForEmeralds(Items.RED_DYE, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.WHITE_DYE, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.BLUE_DYE, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.PINK_DYE, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.BLACK_DYE, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.GREEN_DYE, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.LIGHT_GRAY_DYE, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.MAGENTA_DYE, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.YELLOW_DYE, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.GRAY_DYE, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.PURPLE_DYE, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.LIGHT_BLUE_DYE, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.LIME_DYE, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.ORANGE_DYE, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.BROWN_DYE, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.CYAN_DYE, 1, 16, 4, 0)
            },
            4
    );
    public static final Pair<VillagerTrades.ItemListing[], Integer> saplingPool = Pair.of(
            new VillagerTrades.ItemListing[]{
                    new VillagerTrades.ItemsForEmeralds(Items.ACACIA_SAPLING, 1, 4, 16, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.BIRCH_SAPLING, 1, 4, 16, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.DARK_OAK_SAPLING, 1, 4, 16, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.JUNGLE_SAPLING, 1, 4, 16, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.OAK_SAPLING, 1, 4, 16, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.SPRUCE_SAPLING, 1, 4, 16, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.CHERRY_SAPLING, 1, 4, 16, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.MANGROVE_PROPAGULE, 1, 4, 16, 0)
            },
            2
    );
    public static final Pair<VillagerTrades.ItemListing[], Integer> seedPool = Pair.of(
            new VillagerTrades.ItemListing[]{
                    new VillagerTrades.ItemsForEmeralds(Items.WHEAT_SEEDS, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.BEETROOT_SEEDS, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.PUMPKIN_SEEDS, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.MELON_SEEDS, 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(ModItems.CABBAGE_SEEDS.get(), 1, 16, 4, 0),
                    new VillagerTrades.ItemsForEmeralds(ModItems.TOMATO_SEEDS.get(), 1, 16, 4, 0)
            },
            2
    );
    public static final Pair<VillagerTrades.ItemListing[], Integer> cropPool = Pair.of(
            new VillagerTrades.ItemListing[]{
                    new VillagerTrades.ItemsForEmeralds(Items.CARROT, 1, 4, 16, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.POTATO, 1, 4, 16, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.CACTUS, 1, 4, 16, 0),
                    new VillagerTrades.ItemsForEmeralds(Items.COCOA_BEANS, 1, 4, 16, 0),
                    new VillagerTrades.ItemsForEmeralds(ModItems.ONION.get(), 1, 4, 16, 0),
                    new VillagerTrades.ItemsForEmeralds(ModItems.RICE.get(), 1, 4, 16, 0)
            },
            2
    );
}
