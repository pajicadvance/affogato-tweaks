package me.pajic.affogatotweaks.mixin.trade;

import com.google.common.collect.ImmutableList;
import me.pajic.affogatotweaks.trade.WanderingTraderPools;
import net.minecraft.world.entity.npc.VillagerTrades;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VillagerTrades.class)
public class VillagerTradesMixin {

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/google/common/collect/ImmutableList$Builder;build()Lcom/google/common/collect/ImmutableList;"
            )
    )
    private static ImmutableList<Pair<VillagerTrades.ItemListing[], Integer>> replaceWanderingTraderTrades(ImmutableList.Builder<Pair<VillagerTrades.ItemListing[], Integer>> instance) {
        return ImmutableList.<Pair<VillagerTrades.ItemListing[], Integer>>builder()
                .add(WanderingTraderPools.conversionPool)
                .add(WanderingTraderPools.xpBottlePool)
                .add(WanderingTraderPools.explorerMapPool)
                .add(WanderingTraderPools.dyePool)
                .add(WanderingTraderPools.saplingPool)
                .add(WanderingTraderPools.seedPool)
                .add(WanderingTraderPools.cropPool)
                .build();
    }
}
