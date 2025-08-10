package me.pajic.affogatotweaks.mixin.trade;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public class VillagerMixin {

    @Redirect(
            method = "rewardTradeXp",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"
            )
    )
    private boolean preventXpOrbSpawn(Level instance, Entity entity) {
        return false;
    }

    @Inject(
            method = "startTrading",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cancelStartTrading(Player player, CallbackInfo ci) {
        ci.cancel();
    }
}
