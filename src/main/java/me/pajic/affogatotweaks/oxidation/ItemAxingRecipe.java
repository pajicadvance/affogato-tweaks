package me.pajic.affogatotweaks.oxidation;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ItemAxingRecipe extends CustomRecipe {

    public ItemAxingRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (input.size() != 2) {
            return false;
        } else {
            boolean hasDewaxableItem = false;
            boolean hasOxidizableItem = false;
            boolean hasAxe = false;

            for (int i = 0; i < input.size(); i++) {
                ItemStack itemStack = input.getItem(i);
                if (!itemStack.isEmpty()) {
                    if (itemStack.is(ItemTags.AXES) && !itemStack.has(OxidationData.WAXED)) {
                        if (hasAxe) return false;
                        hasAxe = true;
                    } else {
                        if (!itemStack.is(OxidationData.OXIDIZABLE)) return false;
                        if (hasDewaxableItem || hasOxidizableItem) return false;
                        if (itemStack.has(OxidationData.WAXED)) hasDewaxableItem = true;
                        else hasOxidizableItem = true;
                    }
                }
            }

            return hasAxe && (hasDewaxableItem || hasOxidizableItem);
        }
    }

    @Override
    public @NotNull ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack itemStack = ItemStack.EMPTY;
        for (int i = 0; i < input.size(); i++) {
            ItemStack itemStack2 = input.getItem(i);
            if (itemStack2.is(OxidationData.OXIDIZABLE)) {
                if (itemStack2.has(OxidationData.WAXED)) {
                    itemStack = itemStack2.copy();
                    itemStack.remove(OxidationData.WAXED);
                } else if (OxidationUtil.getItemOxidation(itemStack2) > 0) {
                    itemStack = itemStack2.copy();
                    OxidationUtil.incrementItemOxidation(itemStack, -1);
                }
            }
        }
        return itemStack;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public @NotNull NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> items = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        for (int i = 0; i < input.size(); i++) {
            ItemStack itemStack = input.getItem(i);
            if (itemStack.is(ItemTags.AXES) && !itemStack.has(OxidationData.WAXED)) {
                ItemStack itemStack2 = itemStack.copy();
                itemStack2.setDamageValue(itemStack2.getDamageValue() + 1);
                items.set(i, itemStack2);
            }
        }
        return items;
    }

    @Override
    public @NotNull RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return OxidationData.ITEM_AXING;
    }
}
