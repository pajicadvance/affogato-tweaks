package me.pajic.affogatotweaks.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.spawner.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

// Modifies phantom spawning to be based on the player's height level instead of time since last slept
@Mixin(PhantomSpawner.class)
public class PhantomSpawnerMixin {

    @Unique
    private BlockPos playerBlockPos;

    // Captures the player's current block position
    @Inject(method = "spawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(III)I", shift = At.Shift.BEFORE),locals = LocalCapture.CAPTURE_FAILHARD)
    private void getPlayerBlockPos(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals, CallbackInfoReturnable<Integer> cir, Random random, int i, Iterator var6, ServerPlayerEntity serverPlayerEntity, BlockPos blockPos, LocalDifficulty localDifficulty, ServerStatHandler serverStatHandler) {
        playerBlockPos = blockPos;
    }

    // Changes the spawn condition to the player's height level
    @Redirect(method = "spawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(III)I"))
    private int changeSpawnCondition(int value, int min, int max) {
        return playerBlockPos.getY();
    }

    // Changes the value which the condition is checked against
    @ModifyConstant(method = "spawn", constant = @Constant(intValue = 72000))
    private int changeConditionCheckValue(int constant) {
        return 160;
    }
}
