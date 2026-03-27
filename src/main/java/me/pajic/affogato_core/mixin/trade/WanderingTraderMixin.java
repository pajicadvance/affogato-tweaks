package me.pajic.affogato_core.mixin.trade;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.trade.WanderingTraderPools;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.npc.wanderingtrader.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WanderingTrader.class)
public abstract class WanderingTraderMixin extends AbstractVillager {

    public WanderingTraderMixin(EntityType<? extends AbstractVillager> type, Level level) {
        super(type, level);
    }

    @WrapMethod(method = "updateTrades")
    private void replaceTrades(ServerLevel level, Operation<Void> original) {
        if (Main.CONFIG.features.affogatoWanderingTraderTrades.get()) {
            MerchantOffers offers = getOffers();
            addOffersFromTradeSet(level, offers, WanderingTraderPools.WANDERING_TRADER_XP_BOTTLE);
            addOffersFromTradeSet(level, offers, WanderingTraderPools.WANDERING_TRADER_CONVERSION);
            addOffersFromTradeSet(level, offers, WanderingTraderPools.WANDERING_TRADER_CROP);
            addOffersFromTradeSet(level, offers, WanderingTraderPools.WANDERING_TRADER_SEED);
            addOffersFromTradeSet(level, offers, WanderingTraderPools.WANDERING_TRADER_SAPLING);
            addOffersFromTradeSet(level, offers, WanderingTraderPools.WANDERING_TRADER_DYE);
        }
        else original.call(level);
    }

    @Redirect(
            method = "rewardTradeXp",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"
            )
    )
    private boolean preventXpOrbSpawn(Level instance, Entity entity) {
        return !Main.CONFIG.features.villagerNuke.get();
    }

    @Inject(
            method = "mobInteract",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/npc/wanderingtrader/WanderingTrader;setTradingPlayer(Lnet/minecraft/world/entity/player/Player;)V"
            ),
            cancellable = true
    )
    private void cancelTrading(Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
        if (!Main.CONFIG.features.allowWanderingTraderTrading.get()) cir.setReturnValue(InteractionResult.CONSUME);
    }
}
