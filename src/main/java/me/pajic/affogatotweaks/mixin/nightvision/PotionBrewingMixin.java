package me.pajic.affogatotweaks.mixin.nightvision;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PotionBrewing.class)
public abstract class PotionBrewingMixin {

    @Definition(id = "NIGHT_VISION", field = "Lnet/minecraft/world/item/alchemy/Potions;NIGHT_VISION:Lnet/minecraft/core/Holder;")
    @Expression("?.?(?, ?, @(NIGHT_VISION))")
    @ModifyExpressionValue(
            method = "addVanillaMixes",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static Holder<Potion> changeNightVisionRecipeToInvisibilityRecipe(Holder<Potion> original) {
        return Potions.INVISIBILITY;
    }

    @Definition(id = "NIGHT_VISION", field = "Lnet/minecraft/world/item/alchemy/Potions;NIGHT_VISION:Lnet/minecraft/core/Holder;")
    @Expression("?.?(NIGHT_VISION, ?, ?)")
    @WrapWithCondition(
            method = "addVanillaMixes",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static boolean disableRecipesWithNightVision(PotionBrewing.Builder instance, Holder<Potion> input, Item reagent, Holder<Potion> result) {
        return false;
    }
}