package me.pajic.affogatotweaks.mixin;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProtectionEnchantment.class)
public abstract class ProtectionEnchantmentMixin {

    @Shadow @Final public ProtectionEnchantment.Type type;

    // Changes all protection types to be mutually exclusive with each other,
    // instead of allowing Feather Falling with other protection types
    @Inject(method = "checkCompatibility", at = @At("HEAD"), cancellable = true)
    private void changeCanAccept(Enchantment other, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!(other instanceof ProtectionEnchantment) && (Enchantment) ((Object) this) != other);
    }

    // Makes the Protection enchantment not reduce damage from damage types covered by other protection enchantments
    @Inject(method = "getDamageProtection", at = @At("HEAD"), cancellable = true)
    private void replaceGetProtectionAmount(int level, DamageSource source, CallbackInfoReturnable<Integer> cir) {
        cir.cancel();
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            cir.setReturnValue(0);
        } else if (type == ProtectionEnchantment.Type.ALL
                && !source.is(DamageTypeTags.IS_FIRE) && !source.is(DamageTypeTags.IS_FALL)
                && !source.is(DamageTypeTags.IS_EXPLOSION) && !source.is(DamageTypeTags.IS_PROJECTILE)
                && !source.is(DamageTypes.MAGIC)) {
            cir.setReturnValue(level);
        } else if (type == ProtectionEnchantment.Type.FIRE && source.is(DamageTypeTags.IS_FIRE)) {
            cir.setReturnValue(level * 2);
        } else if (type == ProtectionEnchantment.Type.FALL && source.is(DamageTypeTags.IS_FALL)) {
            cir.setReturnValue(level * 3);
        } else if (type == ProtectionEnchantment.Type.EXPLOSION && source.is(DamageTypeTags.IS_EXPLOSION)) {
            cir.setReturnValue(level * 2);
        } else {
            cir.setReturnValue(type == ProtectionEnchantment.Type.PROJECTILE && source.is(DamageTypeTags.IS_PROJECTILE) ? level * 2 : 0);
        }
    }
}
