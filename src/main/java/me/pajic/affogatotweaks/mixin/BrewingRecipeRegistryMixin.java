package me.pajic.affogatotweaks.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BrewingRecipeRegistry.class)
public abstract class BrewingRecipeRegistryMixin {

    @Shadow
    public static void registerPotionRecipe(Potion input, Item item, Potion output) {
    }

    // Disables the recipe for the night vision potion
    @Redirect(method = "registerDefaults", at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/BrewingRecipeRegistry;registerPotionRecipe(Lnet/minecraft/potion/Potion;Lnet/minecraft/item/Item;Lnet/minecraft/potion/Potion;)V", ordinal = 10))
    private static void disableNightVisionRecipe(Potion input, Item item, Potion output) {
    }

    // Changes the recipe for the invisibility potion to the recipe of the night vision potion
    @Redirect(method = "registerDefaults", at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/BrewingRecipeRegistry;registerPotionRecipe(Lnet/minecraft/potion/Potion;Lnet/minecraft/item/Item;Lnet/minecraft/potion/Potion;)V", ordinal = 12))
    private static void changeInvisibilityRecipe(Potion input, Item item, Potion output) {
        registerPotionRecipe(Potions.AWKWARD, Items.GOLDEN_CARROT, Potions.INVISIBILITY);
    }
}
