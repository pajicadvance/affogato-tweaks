package me.pajic.affogatotweaks.mixin.gameplay;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogatotweaks.values.ArmorDefenseValues;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Attributes.class)
public class AttributeMixin {

    @Definition(id = "ARMOR", field = "Lnet/minecraft/world/entity/ai/attributes/Attributes;ARMOR:Lnet/minecraft/core/Holder;")
    @Expression("ARMOR = ?(?, new ?(?, ?, ?, @(?)).?(?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static double increaseArmorCap(double original) {
        return 20 * ArmorDefenseValues.MAX_ARMOR_MULT;
    }
}
