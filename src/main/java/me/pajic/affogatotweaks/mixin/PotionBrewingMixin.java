package me.pajic.affogatotweaks.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PotionBrewing.class)
public abstract class PotionBrewingMixin {

    @Shadow
    public static void addMix(Potion input, Item item, Potion output) {
    }

    // Disables the recipe for the night vision potion
    @Redirect(method = "bootStrap", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/alchemy/PotionBrewing;addMix(Lnet/minecraft/world/item/alchemy/Potion;Lnet/minecraft/world/item/Item;Lnet/minecraft/world/item/alchemy/Potion;)V", ordinal = 10))
    private static void disableNightVisionRecipe(Potion potionEntry, Item potionIngredient, Potion potionResult) {
    }

    // Changes the recipe for the invisibility potion to the recipe of the night vision potion
    @Redirect(method = "bootStrap", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/alchemy/PotionBrewing;addMix(Lnet/minecraft/world/item/alchemy/Potion;Lnet/minecraft/world/item/Item;Lnet/minecraft/world/item/alchemy/Potion;)V", ordinal = 12))
    private static void changeInvisibilityRecipe(Potion potionEntry, Item potionIngredient, Potion potionResult) {
        addMix(Potions.AWKWARD, Items.GOLDEN_CARROT, Potions.INVISIBILITY);
    }

}
