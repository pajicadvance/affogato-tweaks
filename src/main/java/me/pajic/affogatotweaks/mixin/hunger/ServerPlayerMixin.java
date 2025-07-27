package me.pajic.affogatotweaks.mixin.hunger;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import me.pajic.affogatotweaks.values.ExhaustionValues;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player {
    public ServerPlayerMixin(Level level, BlockPos pos, float yRot, GameProfile gameProfile) {
        super(level, pos, yRot, gameProfile);
    }

    @ModifyExpressionValue(
            method = "checkMovementStatistics",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=0.1"
            )
    )
    private float sprintExhaustion(float original) {
        return ExhaustionValues.sprint();
    }

    @ModifyExpressionValue(
            method = "checkMovementStatistics",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=0.0",
                    ordinal = 0
            )
    )
    private float sneakExhaustion(float original) {
        return ExhaustionValues.sneak();
    }

    @ModifyExpressionValue(
            method = "checkMovementStatistics",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=0.01",
                    ordinal = 0
            )
    )
    private float swimExhaustion(float original) {
        return ExhaustionValues.swimFast();
    }

    @ModifyExpressionValue(
            method = "checkMovementStatistics",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=0.01",
                    ordinal = 2
            )
    )
    private float walkUnderwaterExhaustion(float original) {
        return ExhaustionValues.swimSlow();
    }

    @ModifyExpressionValue(
            method = "checkMovementStatistics",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=0.01",
                    ordinal = 4
            )
    )
    private float walkOnWaterExhaustion(float original) {
        return ExhaustionValues.swimSlow();
    }

    @Inject(
            method = "checkMovementStatistics",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/stats/Stats;CLIMB_ONE_CM:Lnet/minecraft/resources/ResourceLocation;"
            )
    )
    private void climbExhaustion(double dx, double dy, double dz, CallbackInfo ci) {
        causeFoodExhaustion(ExhaustionValues.climb() * Math.round(dy * 100) * 0.01F);
    }

    @Inject(
            method = "checkMovementStatistics",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerPlayer;isSwimming()Z"
            )
    )
    private void crawlExhaustion(double dx, double dy, double dz, CallbackInfo ci) {
        if (isVisuallyCrawling()) causeFoodExhaustion(ExhaustionValues.crawl() * Math.round((float) Math.sqrt(dx * dx + dz * dz) * 100.0F) * 0.01F);
    }
}
