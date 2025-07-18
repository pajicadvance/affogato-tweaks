package me.pajic.affogatotweaks.mixin.food;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {

    @ModifyExpressionValue(
            method = "checkMovementStatistics",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=0.1"
            )
    )
    private float reduceSprintExhaustion(float original) {
        return 0.05F;
    }
}
