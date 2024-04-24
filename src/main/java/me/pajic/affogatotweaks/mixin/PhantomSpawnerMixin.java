package me.pajic.affogatotweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.levelgen.PhantomSpawner;

// Modifies phantom spawning to be based on the player's height level instead of time since last slept
@Mixin(PhantomSpawner.class)
public class PhantomSpawnerMixin {

    @Unique
    private BlockPos playerBlockPos;

    // Captures the player's current block position
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(III)I", shift = At.Shift.BEFORE),locals = LocalCapture.CAPTURE_FAILHARD)
    private void getPlayerBlockPos(ServerLevel world, boolean spawnMonsters, boolean spawnAnimals, CallbackInfoReturnable<Integer> cir, RandomSource random, int i, Iterator var6, ServerPlayer serverPlayerEntity, BlockPos blockPos, DifficultyInstance localDifficulty, ServerStatsCounter serverStatHandler) {
        playerBlockPos = blockPos;
    }

    // Changes the spawn condition to the player's height level
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(III)I"))
    private int changeSpawnCondition(int value, int min, int max) {
        return playerBlockPos.getY();
    }

    // Changes the value which the condition is checked against
    @ModifyConstant(method = "tick", constant = @Constant(intValue = 72000))
    private int changeConditionCheckValue(int constant) {
        return 160;
    }

    // Modifies the rate at which the spawn check runs
    @ModifyConstant(method = "tick", constant = @Constant(intValue = 20, ordinal = 0))
    private int modifySpawnCheckTimer(int constant) {
        return 10;
    }
}
