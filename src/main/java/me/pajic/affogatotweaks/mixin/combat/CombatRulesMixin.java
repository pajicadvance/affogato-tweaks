package me.pajic.affogatotweaks.mixin.combat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogatotweaks.values.ArmorDefenseValues;
import net.minecraft.world.damagesource.CombatRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(CombatRules.class)
public class CombatRulesMixin {

    @ModifyArgs(
            method = "getDamageAfterAbsorb",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/Mth;clamp(FFF)F",
                    ordinal = 0
            )
    )
    private static void modifyReduction(
            Args args,
            @Local(argsOnly = true, ordinal = 0) float damage,
            @Local(argsOnly = true, ordinal = 1) float armorValue,
            @Local(argsOnly = true, ordinal = 2) float armorToughness
    ) {
        args.set(0, armorValue - (ArmorDefenseValues.MAX_ARMOR_MULT * damage) / (2.0F + armorToughness / 4.0F));
        args.set(1, (float) args.get(1) * ArmorDefenseValues.MAX_ARMOR_MULT);
        args.set(2, (float) args.get(2) * ArmorDefenseValues.MAX_ARMOR_MULT);
    }

    @ModifyExpressionValue(
            method = "getDamageAfterAbsorb",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=25.0"
            )
    )
    private static float modifyProtectionDivisor(float original) {
        return original * ArmorDefenseValues.MAX_ARMOR_MULT;
    }
}
