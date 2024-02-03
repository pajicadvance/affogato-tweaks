package me.pajic.affogatotweaks.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Shadow public abstract boolean isCreative();

    @Shadow public abstract void setReducedDebugInfo(boolean reducedDebugInfo);

    @Inject(method = "tick", at = @At("HEAD"))
    private void setReducedDebugInfo(CallbackInfo ci) {
        setReducedDebugInfo(!isCreative());
    }
}
