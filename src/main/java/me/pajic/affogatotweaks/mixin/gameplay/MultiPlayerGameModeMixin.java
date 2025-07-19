package me.pajic.affogatotweaks.mixin.gameplay;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
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
        return 2;
    }
}