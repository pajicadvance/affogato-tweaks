package me.pajic.affogato_core.trade;

import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.item.Items;
import org.apache.commons.lang3.tuple.Pair;
import vectorwing.farmersdelight.common.registry.ModItems;

public class WanderingTraderFDPools {
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
