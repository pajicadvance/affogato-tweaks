package me.pajic.affogatotweaks.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProtectionEnchantment.class)
public abstract class ProtectionEnchantmentMixin {

    // Changes all protection types to be mutually exclusive with each other,
    // instead of allowing Feather Falling with other protection types
    @Inject(method = "checkCompatibility", at = @At("HEAD"), cancellable = true)
    private void changeCanAccept(Enchantment other, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!(other instanceof ProtectionEnchantment) && (Enchantment) ((Object) this) != other);
    }

    @ModifyReturnValue(method = "getDamageProtection", at = @At(value = "RETURN", ordinal = 1))
    private int aaa(int original, @Local(argsOnly = true) DamageSource source) {
        if (!source.is(DamageTypeTags.IS_FIRE) && !source.is(DamageTypeTags.IS_FALL)
                && !source.is(DamageTypeTags.IS_EXPLOSION) && !source.is(DamageTypeTags.IS_PROJECTILE)
                && !source.is(DamageTypes.MAGIC)) {
            return original;
        }
        else {
            return 0;
        }
    }
}
