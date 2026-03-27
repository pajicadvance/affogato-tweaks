package me.pajic.affogato_core.trade;

import me.pajic.affogato_core.Main;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.TradeSet;

public class WanderingTraderPools {
    public static final ResourceKey<TradeSet> WANDERING_TRADER_CONVERSION = ResourceKey.create(Registries.TRADE_SET, Main.id("wandering_trader/conversion"));
    public static final ResourceKey<TradeSet> WANDERING_TRADER_CROP = ResourceKey.create(Registries.TRADE_SET, Main.id("wandering_trader/crop"));
    public static final ResourceKey<TradeSet> WANDERING_TRADER_DYE = ResourceKey.create(Registries.TRADE_SET, Main.id("wandering_trader/dye"));
    public static final ResourceKey<TradeSet> WANDERING_TRADER_SAPLING = ResourceKey.create(Registries.TRADE_SET, Main.id("wandering_trader/sapling"));
    public static final ResourceKey<TradeSet> WANDERING_TRADER_SEED = ResourceKey.create(Registries.TRADE_SET, Main.id("wandering_trader/seed"));
    public static final ResourceKey<TradeSet> WANDERING_TRADER_XP_BOTTLE = ResourceKey.create(Registries.TRADE_SET, Main.id("wandering_trader/xp_bottle"));
}
