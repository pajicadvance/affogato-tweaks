package me.pajic.affogatotweaks.mixin;

import net.minecraft.world.entity.monster.Monster;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Monster.class)
public class MonsterMixin {

    // Prevents hostile entities from dropping experience on death
    @Inject(method = "shouldDropExperience", at = @At("RETURN"), cancellable = true)
    private void preventEntityXpDrops(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
