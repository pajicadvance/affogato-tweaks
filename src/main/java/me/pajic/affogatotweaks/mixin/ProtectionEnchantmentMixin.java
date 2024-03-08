package me.pajic.affogatotweaks.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.DamageTypeTags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProtectionEnchantment.class)
public abstract class ProtectionEnchantmentMixin {

    @Shadow @Final public ProtectionEnchantment.Type protectionType;

    // Changes all protection types to be mutually exclusive with each other,
    // instead of allowing Feather Falling with other protection types
    @Inject(method = "canAccept", at = @At("HEAD"), cancellable = true)
    private void changeCanAccept(Enchantment other, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!(other instanceof ProtectionEnchantment) && (Enchantment) ((Object) this) != other);
    }

    // Makes the Protection enchantment not reduce damage from damage types covered by other protection enchantments
    @Inject(method = "getProtectionAmount", at = @At("HEAD"), cancellable = true)
    private void replaceGetProtectionAmount(int level, DamageSource source, CallbackInfoReturnable<Integer> cir) {
        cir.cancel();
        if (source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            cir.setReturnValue(0);
        } else if (protectionType == ProtectionEnchantment.Type.ALL
                && !source.isIn(DamageTypeTags.IS_FIRE) && !source.isIn(DamageTypeTags.IS_FALL)
                && !source.isIn(DamageTypeTags.IS_EXPLOSION) && !source.isIn(DamageTypeTags.IS_PROJECTILE)) {
            cir.setReturnValue(level);
        } else if (protectionType == ProtectionEnchantment.Type.FIRE && source.isIn(DamageTypeTags.IS_FIRE)) {
            cir.setReturnValue(level * 2);
        } else if (protectionType == ProtectionEnchantment.Type.FALL && source.isIn(DamageTypeTags.IS_FALL)) {
            cir.setReturnValue(level * 3);
        } else if (protectionType == ProtectionEnchantment.Type.EXPLOSION && source.isIn(DamageTypeTags.IS_EXPLOSION)) {
            cir.setReturnValue(level * 2);
        } else {
            cir.setReturnValue(protectionType == ProtectionEnchantment.Type.PROJECTILE && source.isIn(DamageTypeTags.IS_PROJECTILE) ? level * 2 : 0);
        }
    }
}
