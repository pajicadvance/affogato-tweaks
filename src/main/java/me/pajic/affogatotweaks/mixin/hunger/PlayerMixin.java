package me.pajic.affogatotweaks.mixin.hunger;

import me.pajic.affogatotweaks.values.ExhaustionValues;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Shadow public abstract void causeFoodExhaustion(float exhaustion);

    @ModifyArg(
            method = "jumpFromGround",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V",
                    ordinal = 0
            )
    )
    private float jumpSprintExhaustion(float original) {
        return ExhaustionValues.JUMP_SPRINT;
    }

    @ModifyArg(
            method = "jumpFromGround",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V",
                    ordinal = 1
            )
    )
    private float jumpExhaustion(float original) {
        return ExhaustionValues.JUMP;
    }

    @ModifyArg(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V"
            )
    )
    private float attackExhaustion(float original) {
        return ExhaustionValues.ATTACK;
    }

    @ModifyArg(
            method = "actuallyHurt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V"
            )
    )
    private float takeDamageExhaustion(float exhaustion) {
        return exhaustion == 0.1F ? ExhaustionValues.TAKE_DAMAGE : exhaustion;
    }

    @Inject(
            method = "hurtCurrentlyUsedShield",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;awardStat(Lnet/minecraft/stats/Stat;)V"
            )
    )
    private void blockAttackExhaustion(float damageAmount, CallbackInfo ci) {
        causeFoodExhaustion(ExhaustionValues.BLOCK_ATTACK);
    }
}
