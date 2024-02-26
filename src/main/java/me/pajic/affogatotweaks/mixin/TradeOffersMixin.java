package me.pajic.affogatotweaks.mixin;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(TradeOffers.class)
public abstract class TradeOffersMixin {

    // Removes the bottle of enchanting trade from the cleric villager
    @ModifyArg(method = "method_16929", at = @At(value = "INVOKE", target = "Lnet/minecraft/village/TradeOffers;copyToFastUtilMap(Lcom/google/common/collect/ImmutableMap;)Lit/unimi/dsi/fastutil/ints/Int2ObjectMap;", ordinal = 6))
    private static ImmutableMap<Integer, TradeOffers.Factory[]> modifyClericTrades(ImmutableMap<Integer, TradeOffers.Factory[]> map) {
        return ImmutableMap.of(1,
                new TradeOffers.Factory[]{
                        new TradeOffers.BuyForOneEmeraldFactory(Items.ROTTEN_FLESH, 32, 16, 2),
                        new TradeOffers.SellItemFactory(Items.REDSTONE, 1, 2, 1)}, 2,
                new TradeOffers.Factory[]{
                        new TradeOffers.BuyForOneEmeraldFactory(Items.GOLD_INGOT, 3, 12, 10),
                        new TradeOffers.SellItemFactory(Items.LAPIS_LAZULI, 1, 1, 5)}, 3,
                new TradeOffers.Factory[]{
                        new TradeOffers.BuyForOneEmeraldFactory(Items.RABBIT_FOOT, 2, 12, 20),
                        new TradeOffers.SellItemFactory(Blocks.GLOWSTONE, 4, 1, 12, 10)}, 4,
                new TradeOffers.Factory[]{
                        new TradeOffers.BuyForOneEmeraldFactory(Items.SCUTE, 4, 12, 30),
                        new TradeOffers.BuyForOneEmeraldFactory(Items.GLASS_BOTTLE, 9, 12, 30),
                        new TradeOffers.SellItemFactory(Items.ENDER_PEARL, 5, 1, 15)}, 5,
                new TradeOffers.Factory[]{
                        new TradeOffers.BuyForOneEmeraldFactory(Items.NETHER_WART, 22, 12, 30)});
    }
}
