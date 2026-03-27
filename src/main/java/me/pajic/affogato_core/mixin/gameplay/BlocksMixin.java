package me.pajic.affogato_core.mixin.gameplay;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogato_core.Main;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Blocks.class)
public class BlocksMixin {

    @ModifyExpressionValue(
            method = "lambda$static$60",
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=14"
            )
    )
    private static int setTorchLightLevel(int original) {
        return Main.CONFIG.misc.torchLightLevel.get();
    }

    @ModifyExpressionValue(
            method = "lambda$static$62",
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=14"
            )
    )
    private static int setWallTorchLightLevel(int original) {
        return Main.CONFIG.misc.torchLightLevel.get();
    }

    @ModifyExpressionValue(
            method = "lambda$static$135",
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=14"
            )
    )
    private static int setCopperTorchLightLevel(int original) {
        return Main.CONFIG.misc.copperTorchLightLevel.get();
    }

    @ModifyExpressionValue(
            method = "lambda$static$137",
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=14"
            )
    )
    private static int setCopperWallTorchLightLevel(int original) {
        return Main.CONFIG.misc.copperTorchLightLevel.get();
    }

    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=0.98F"
            )
    )
    private static float setIceSlipperiness(float original) {
        return Main.CONFIG.misc.iceFriction.get();
    }

    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=0.989F"
            )
    )
    private static float setBlueIceSlipperiness(float original) {
        return Main.CONFIG.misc.blueIceFriction.get();
    }
}
