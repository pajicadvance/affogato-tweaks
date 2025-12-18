package me.pajic.affogato_core.mixin.raid;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.raid.ServerLevelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.BadOmenMobEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BadOmenMobEffect.class)
public class BadOmenMobEffectMixin {

    @ModifyExpressionValue(
            method = "applyEffectTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;isVillage(Lnet/minecraft/core/BlockPos;)Z"
            )
    )
    private boolean raidStartsInPillagerOutpost(boolean original, @Local(argsOnly = true) ServerLevel level, @Local ServerPlayer player) {
        if (Main.CONFIG.features.raidRework.get()) {
            BlockPos pos = level.findNearestMapStructure(Main.OUTPOSTS, player.getOnPos(), 1, false);
            if (pos != null && player.distanceToSqr(pos.getX(), player.getY(), pos.getZ()) < 1024) {
                ((ServerLevelAccess) level).affogatotweaks$getClearedOutposts().addClearedOutpost(pos);
                return true;
            }
            return false;
        }
        return original;
    }
}