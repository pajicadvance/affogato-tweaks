package me.pajic.affogatotweaks.mixin.raid;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Pair;
import me.pajic.affogatotweaks.raid.ServerLevelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
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
        HolderSet<Structure> outpost = HolderSet.direct(level.registryAccess().registryOrThrow(Registries.STRUCTURE).getHolderOrThrow(BuiltinStructures.PILLAGER_OUTPOST));
        Pair<BlockPos, Holder<Structure>> pair = level.getChunkSource().getGenerator().findNearestMapStructure(level, outpost, player.getOnPos(), 1, false);
        if (pair != null && player.distanceToSqr(pair.getFirst().getX(), player.getY(), pair.getFirst().getZ()) < 1024) {
            ((ServerLevelAccess) level).affogatotweaks$getClearedOutposts().addClearedOutpost(pair.getFirst());
            return true;
        }
        return false;
    }
}