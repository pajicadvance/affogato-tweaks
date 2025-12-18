package me.pajic.affogato_core.mixin.trade;

import com.google.common.collect.ImmutableList;
import me.pajic.affogato_core.CompatFlags;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.trade.WanderingTraderFDPools;
import me.pajic.affogato_core.trade.WanderingTraderPools;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = VillagerTrades.class, priority = 1500)
public class VillagerTradesMixin {

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/google/common/collect/ImmutableList$Builder;build()Lcom/google/common/collect/ImmutableList;"
            )
    )
    private static ImmutableList<Pair<VillagerTrades.ItemListing[], Integer>> replaceWanderingTraderTrades(
            ImmutableList.Builder<Pair<VillagerTrades.ItemListing[], Integer>> instance
    ) {
        if (Main.CONFIG.features.affogatoWanderingTraderTrades.get()) {
            ImmutableList.Builder<Pair<VillagerTrades.ItemListing[], Integer>> builder =
                    ImmutableList.<Pair<VillagerTrades.ItemListing[], Integer>>builder()
                            .add(WanderingTraderPools.conversionPool)
                            .add(WanderingTraderPools.xpBottlePool)
                            .add(WanderingTraderPools.explorerMapPool)
                            .add(WanderingTraderPools.dyePool)
                            .add(WanderingTraderPools.saplingPool);
            if (CompatFlags.FD_LOADED) {
                builder.add(WanderingTraderFDPools.cropPool).add(WanderingTraderFDPools.seedPool);
            } else {
                builder.add(WanderingTraderPools.cropPool).add(WanderingTraderPools.seedPool);
            }
            return builder.build();
        }
        return instance.build();
    }
}
