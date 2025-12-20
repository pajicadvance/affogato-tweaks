package me.pajic.affogato_core.mixin.trade;

import me.pajic.affogato_core.Main;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.wanderingtrader.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WanderingTrader.class)
public class WanderingTraderMixin {

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
