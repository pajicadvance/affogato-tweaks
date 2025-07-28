package me.pajic.affogatotweaks.mixin.gameplay;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogatotweaks.values.MiscValues;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {

    @ModifyExpressionValue(
            method = "continueDestroyBlock",
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=5"
            )
    )
    private int reduceDestroyDelay(int value) {
        return MiscValues.MINING_DELAY_TICKS;
    }
}