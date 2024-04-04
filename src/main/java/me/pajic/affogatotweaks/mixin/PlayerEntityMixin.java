package me.pajic.affogatotweaks.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Shadow public abstract boolean isCreative();

    @Shadow public abstract boolean isSpectator();

    @Shadow public abstract void setReducedDebugInfo(boolean reducedDebugInfo);

    // Turns on reduced debug info if the player is not in creative or spectator mode
    @Inject(method = "tick", at = @At("HEAD"))
    private void setReducedDebugInfo(CallbackInfo ci) {
        setReducedDebugInfo(!isCreative() && !isSpectator());
    }

    // Prevents player entities from dropping experience
    @Inject(method = "shouldAlwaysDropXp", at = @At("RETURN"), cancellable = true)
    private void preventPlayerEntityXpDrops1(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Inject(method = "getXpToDrop", at = @At("RETURN"), cancellable = true)
    private void preventPlayerEntityXpDrops2(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(0);
    }
}
