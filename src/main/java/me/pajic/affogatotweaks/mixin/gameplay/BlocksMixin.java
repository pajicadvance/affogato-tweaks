package me.pajic.affogatotweaks.mixin.gameplay;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogatotweaks.values.MiscValues;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Blocks.class)
public class BlocksMixin {

    @ModifyExpressionValue(
            method = "method_26152",
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=14"
            )
    )
    private static int setTorchLightLevel(int original) {
        return MiscValues.TORCH_LIGHT_LEVEL;
    }

    @ModifyExpressionValue(
            method = "method_26136",
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=14"
            )
    )
    private static int setWallTorchLightLevel(int original) {
        return MiscValues.TORCH_LIGHT_LEVEL;
    }

    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=0.98F"
            )
    )
    private static float setIceSlipperiness(float original) {
        return MiscValues.ICE_FRICTION;
    }

    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=0.989F"
            )
    )
    private static float setBlueIceSlipperiness(float original) {
        return MiscValues.BLUE_ICE_FRICTION;
    }
}
