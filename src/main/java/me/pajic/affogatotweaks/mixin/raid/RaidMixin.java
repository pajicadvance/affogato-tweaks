package me.pajic.affogatotweaks.mixin.raid;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import me.pajic.affogatotweaks.raid.ClearedOutposts;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.raid.Raid;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Raid.class)
public class RaidMixin {
    @Shadow @Final private ServerLevel level;
    @Shadow private BlockPos center;

    @ModifyExpressionValue(
            method = "tick",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/entity/raid/Raid$RaidStatus;LOSS:Lnet/minecraft/world/entity/raid/Raid$RaidStatus;"
            )
    )
    private Raid.RaidStatus noDefeat(Raid.RaidStatus original) {
        return Raid.RaidStatus.ONGOING;
    }

    @ModifyExpressionValue(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;isVillage(Lnet/minecraft/core/BlockPos;)Z"
            )
    )
    private boolean replaceVillageChecks1(boolean original) {
        return ClearedOutposts.isPillagerOutpost(level, center);
    }

    @ModifyExpressionValue(
            method = "updateRaiders",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;isVillage(Lnet/minecraft/core/BlockPos;)Z"
            )
    )
    private boolean replaceVillageChecks2(boolean original) {
        return ClearedOutposts.isPillagerOutpost(level, center);
    }

    @ModifyExpressionValue(
            method = "findRandomSpawnPos",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;isVillage(Lnet/minecraft/core/BlockPos;)Z"
            )
    )
    private boolean replaceVillageChecks3(boolean original) {
        return ClearedOutposts.isPillagerOutpost(level, center);
    }

    @WrapWithCondition(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/raid/Raid;moveRaidCenterToNearbyVillageSection()V"
            )
    )
    private boolean preventMoveCenter(Raid instance) {
        return false;
    }
}
