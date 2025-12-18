package me.pajic.affogato_core.mixin.trade;

import me.pajic.affogato_core.Main;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.ShowTradesToPlayer;
import net.minecraft.world.entity.npc.villager.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShowTradesToPlayer.class)
public abstract class ShowTradesToPlayerMixin {

    @Inject(
            method = "start(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/npc/villager/Villager;J)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cancelStart(ServerLevel level, Villager entity, long gameTime, CallbackInfo ci) {
        if (Main.CONFIG.features.villagerNuke.get()) ci.cancel();
    }
}
