package me.pajic.affogatotweaks.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.TippedArrowRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(TippedArrowRecipe.class)
public class TippedArrowRecipeMixin {

    @ModifyArg(method = "matches(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/world/World;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private Item setRequiredPotionType1(Item item) {
        return Items.POTION;
    }

    @ModifyArg(method = "craft(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/registry/DynamicRegistryManager;)Lnet/minecraft/item/ItemStack;", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private Item setRequiredPotionType2(Item item) {
        return Items.POTION;
    }
}
