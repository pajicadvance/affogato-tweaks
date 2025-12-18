package me.pajic.affogato_core.mixin.raid;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.raid.ClearedOutposts;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.PathfindToRaidGoal;
import net.minecraft.world.entity.raid.Raider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@SuppressWarnings("ConstantConditions")
@Mixin(PathfindToRaidGoal.class)
public class PathfindToRaidGoalMixin<T extends Raider> {

    @Shadow @Final private T mob;

    @ModifyExpressionValue(
            method = "canUse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;isVillage(Lnet/minecraft/core/BlockPos;)Z"
            )
    )
    private boolean replaceVillageChecks1(boolean original) {
        return Main.CONFIG.features.raidRework.get() ? ClearedOutposts.isPillagerOutpost((ServerLevel) mob.level(), mob.getCurrentRaid().getCenter()) : original;
    }

    @ModifyExpressionValue(
            method = "canContinueToUse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;isVillage(Lnet/minecraft/core/BlockPos;)Z"
            )
    )
    private boolean replaceVillageChecks2(boolean original) {
        return Main.CONFIG.features.raidRework.get() ? ClearedOutposts.isPillagerOutpost((ServerLevel) mob.level(), mob.getCurrentRaid().getCenter()) : original;
    }
}
