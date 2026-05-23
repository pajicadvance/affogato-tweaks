package me.pajic.affogato_core.mixin.integration.rvb;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import me.pajic.affogato_core.Main;
import net.jurassicbeast.reusablevaultblocks.ReusableVaultBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@IfModLoaded("reusable-vault-blocks")
@Mixin(ReusableVaultBlocks.class)
public class ReusableVaultBlocksMixin {

    @ModifyExpressionValue(
            method = "onInitialize",
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=720000"
            )
    )
    private int modifyRevaultTime(int original) {
        return Main.CONFIG.misc.revaultTime.get();
    }

    @ModifyExpressionValue(
            method = "onInitialize",
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=864000"
            )
    )
    private int modifyOminousRevaultTime(int original) {
        return Main.CONFIG.misc.ominousRevaultTime.get();
    }
}
