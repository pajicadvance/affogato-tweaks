package me.pajic.affogato_core.mixin.raid;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.raid.ClearedOutposts;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.raid.Raid;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Raid.class)
public class RaidMixin {

    @Shadow private BlockPos center;

    @ModifyExpressionValue(
            method = "tick",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/entity/raid/Raid$RaidStatus;LOSS:Lnet/minecraft/world/entity/raid/Raid$RaidStatus;",
                    opcode = Opcodes.GETSTATIC
            )
    )
    private Raid.RaidStatus noDefeat(Raid.RaidStatus original) {
        return Main.CONFIG.features.raidRework.get() ? Raid.RaidStatus.ONGOING : original;
    }

    @ModifyExpressionValue(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;isVillage(Lnet/minecraft/core/BlockPos;)Z"
            )
    )
    private boolean replaceVillageChecks1(boolean original, @Local(argsOnly = true) ServerLevel level) {
        return Main.CONFIG.features.raidRework.get() ? ClearedOutposts.isPillagerOutpost(level, center) : original;
    }

    @ModifyExpressionValue(
            method = "updateRaiders",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;isVillage(Lnet/minecraft/core/BlockPos;)Z"
            )
    )
    private boolean replaceVillageChecks2(boolean original, @Local(argsOnly = true) ServerLevel level) {
        return Main.CONFIG.features.raidRework.get() ? ClearedOutposts.isPillagerOutpost(level, center) : original;
    }

    @ModifyExpressionValue(
            method = "findRandomSpawnPos",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;isVillage(Lnet/minecraft/core/BlockPos;)Z"
            )
    )
    private boolean replaceVillageChecks3(boolean original, @Local(argsOnly = true) ServerLevel level) {
        return Main.CONFIG.features.raidRework.get() ? ClearedOutposts.isPillagerOutpost(level, center) : original;
    }

    @WrapWithCondition(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/raid/Raid;moveRaidCenterToNearbyVillageSection(Lnet/minecraft/server/level/ServerLevel;)V"
            )
    )
    private boolean preventMoveCenter(Raid instance, ServerLevel level) {
        return !Main.CONFIG.features.raidRework.get();
    }
}
