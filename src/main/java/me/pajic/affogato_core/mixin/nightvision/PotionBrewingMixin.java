package me.pajic.affogato_core.mixin.nightvision;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import me.pajic.affogato_core.Main;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PotionBrewing.class)
public abstract class PotionBrewingMixin {

    @Definition(id = "NIGHT_VISION", field = "Lnet/minecraft/world/item/alchemy/Potions;NIGHT_VISION:Lnet/minecraft/core/Holder$Reference;")
    @Expression("?.?(?, ?, @(NIGHT_VISION))")
    @ModifyExpressionValue(
            method = "addVanillaMixes",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static Holder.Reference<Potion> changeNightVisionRecipeToInvisibilityRecipe(Holder.Reference<Potion> original) {
        return Main.CONFIG.features.nightVisionNuke.get() ? Potions.INVISIBILITY : original;
    }

    @Definition(id = "NIGHT_VISION", field = "Lnet/minecraft/world/item/alchemy/Potions;NIGHT_VISION:Lnet/minecraft/core/Holder$Reference;")
    @Expression("?.?(NIGHT_VISION, ?, ?)")
    @WrapWithCondition(
            method = "addVanillaMixes",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static boolean disableRecipesWithNightVision(PotionBrewing.Builder instance, Holder<Potion> input, Item reagent, Holder<Potion> result) {
        return !Main.CONFIG.features.nightVisionNuke.get();
    }
}