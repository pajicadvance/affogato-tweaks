package me.pajic.affogatotweaks.mixin.stats;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogatotweaks.values.ArmorBonusValues;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ArmorItem.class)
public class ArmorItemMixin {

    @ModifyExpressionValue(
            method = "method_56689",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ArmorMaterial;knockbackResistance()F"
            )
    )
    private static float setKnockbackResistValues(float original, @Local(argsOnly = true) Holder<ArmorMaterial> material) {
        return ArmorBonusValues.KNOCKBACK_RESIST.getOrDefault(material, original);
    }

    @ModifyExpressionValue(
            method = "method_56689",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ArmorMaterial;toughness()F"
            )
    )
    private static float setToughnessValues(float original, @Local(argsOnly = true) Holder<ArmorMaterial> material) {
        return ArmorBonusValues.TOUGHNESS.getOrDefault(material, original);
    }
}
