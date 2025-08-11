package me.pajic.affogatotweaks.mixin.gameplay;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.pajic.affogatotweaks.block.WeatheringLightningRodBlock;
import me.pajic.affogatotweaks.tag.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LightningBolt.class)
public abstract class LightningBoltMixin extends Entity {
    public LightningBoltMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    protected abstract BlockPos getStrikePosition();

    @WrapMethod(method = "powerLightningRod")
    private void redirectStrikeMethod(Operation<Void> original) {
        BlockPos blockPos = getStrikePosition();
        BlockState blockState = level().getBlockState(blockPos);
        if (blockState.is(ModTags.LIGHTNING_ROD_VARIANTS)) {
            ((WeatheringLightningRodBlock) blockState.getBlock()).onLightningStrike(blockState, level(), blockPos);
        }
        else original.call();
    }

    @WrapOperation(
            method = "clearCopperOnLightningStrike",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"
            )
    )
    private static boolean modifyLightningRodCheck_clearCopperOnLightningStrike(BlockState instance, Block block, Operation<Boolean> original) {
        return original.call(instance, block) || instance.is(ModTags.LIGHTNING_ROD_VARIANTS);
    }
}
