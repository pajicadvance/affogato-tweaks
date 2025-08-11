package me.pajic.affogatotweaks.mixin.raid;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogatotweaks.raid.ServerLevelAccess;
import me.pajic.affogatotweaks.tag.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.world.effect.BadOmenMobEffect;
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
    private boolean raidStartsInPillagerOutpost(boolean original, @Local ServerLevel level, @Local ServerPlayer player) {
        BlockPos pos = level.findNearestMapStructure(ModTags.OUTPOSTS, player.getOnPos(), 1, false);
        if (pos != null && player.distanceToSqr(pos.getX(), player.getY(), pos.getZ()) < 1024) {
            ((ServerLevelAccess) level).affogatotweaks$getClearedOutposts().addClearedOutpost(pos);
            return true;
        }
        return false;
    }
}