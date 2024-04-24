package me.pajic.affogatotweaks.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.PhantomSpawner;

// Modifies phantom spawning to be based on the player's height level instead of time since last slept
@Mixin(PhantomSpawner.class)
public class PhantomSpawnerMixin {

    // Changes the spawn condition to the player's height level
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(III)I"))
    private int changeSpawnCondition(int value, int min, int max, @Local BlockPos playerBlockPos) {
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
