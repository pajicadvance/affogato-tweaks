package me.pajic.affogatotweaks.mixin.hunger;

import me.pajic.affogatotweaks.values.ExhaustionValues;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Block.class)
public class BlockMixin {

    @ModifyArg(
            method = "playerDestroy",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V"
            )
    )
    private float blockBreakExhaustion(float original) {
        return ExhaustionValues.BREAK_BLOCK;
    }
}
