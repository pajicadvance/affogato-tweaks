package me.pajic.affogatotweaks.mixin;

import net.minecraft.world.entity.monster.Shulker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Shulker.class)
public class ShulkerMixin {

    // Disables shulker duplication mechanic
    @Inject(method = "hitByShulkerBullet", at = @At("HEAD"), cancellable = true)
    private void disableShulkerDuplication(CallbackInfo ci) {
        ci.cancel();
    }
}
