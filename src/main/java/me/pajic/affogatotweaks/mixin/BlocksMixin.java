package me.pajic.affogatotweaks.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Blocks.class)
public class BlocksMixin {

    @Inject(method = "method_26152", at = @At("RETURN"), cancellable = true)
    private static void setTorchLightLevel(BlockState state, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(10);
    }

    @Inject(method = "method_26136", at = @At("RETURN"), cancellable = true)
    private static void setWallTorchLightLevel(BlockState state, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(10);
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 0.98F))
    private static float setIceSlipperiness(float constant) {
        return 0.965F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 0.989F))
    private static float setBlueIceSlipperiness(float constant) {
        return 0.97F;
    }
}
