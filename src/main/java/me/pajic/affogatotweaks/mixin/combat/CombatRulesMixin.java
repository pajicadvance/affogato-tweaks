package me.pajic.affogatotweaks.mixin.combat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogatotweaks.values.ArmorBonusValues;
import me.pajic.affogatotweaks.values.ArmorDefenseValues;
import net.minecraft.world.damagesource.CombatRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CombatRules.class)
public class CombatRulesMixin {

    @ModifyExpressionValue(
            method = "getDamageAfterAbsorb",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=20.0"
            )
    )
    private static float modifyMaxArmorPoints(float original) {
        return ArmorDefenseValues.MAX_ARMOR;
    }

    @ModifyExpressionValue(
            method = "getDamageAfterAbsorb",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=25.0"
            )
    )
    private static float modifyProtectionDivisor(float original) {
        return original * (ArmorDefenseValues.MAX_ARMOR / 20);
    }

    @ModifyExpressionValue(
            method = "getDamageAfterAbsorb",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=2.0"
            )
    )
    private static float modifyBaseToughness(float original) {
        return ArmorBonusValues.BASE_TOUGHNESS;
    }
}
