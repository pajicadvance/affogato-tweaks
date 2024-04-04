package me.pajic.affogatotweaks.mixin;

import net.minecraft.entity.mob.ShulkerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShulkerEntity.class)
public class ShulkerEntityMixin {

    // Disables shulker duplication mechanic
    @Inject(method = "spawnNewShulker", at = @At("HEAD"), cancellable = true)
    private void disableShulkerDuplication(CallbackInfo ci) {
        ci.cancel();
    }
}
