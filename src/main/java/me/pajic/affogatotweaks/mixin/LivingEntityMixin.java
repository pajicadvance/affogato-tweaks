package me.pajic.affogatotweaks.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    // Prevents living entities from dropping experience
    @Inject(method = "shouldDropXp", at = @At("RETURN"), cancellable = true)
    private void preventEntityXpDrops(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
