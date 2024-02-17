package me.pajic.affogatotweaks.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProtectionEnchantment.class)
public abstract class ProtectionEnchantmentMixin {

    @Inject(method = "canAccept", at = @At("HEAD"), cancellable = true)
    private void changeCanAccept(Enchantment other, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!(other instanceof ProtectionEnchantment) && (Enchantment) ((Object) this) != other);
    }
}
