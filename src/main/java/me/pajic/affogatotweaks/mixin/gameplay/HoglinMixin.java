package me.pajic.affogatotweaks.mixin.gameplay;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Hoglin.class)
public class HoglinMixin {

    @WrapMethod(method = "checkHoglinSpawnRules")
    private static boolean modifySpawnRules(EntityType<Hoglin> hoglin, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random, Operation<Boolean> original) {
        return level.getBlockState(pos.below()).is(Blocks.CRIMSON_NYLIUM);
    }
}
