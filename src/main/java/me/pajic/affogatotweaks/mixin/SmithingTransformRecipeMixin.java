package me.pajic.affogatotweaks.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.recipe.SmithingTransformRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static me.pajic.affogatotweaks.item.ModItems.ENCHANTMENT_UPGRADE_SMITHING_TEMPLATE;

@Mixin(SmithingTransformRecipe.class)
public class SmithingTransformRecipeMixin {

    // Defines conditions under which the enchantment upgrade recipe can be used and increments enchanted book enchantment level
    @Inject(method = "craft", at = @At(value = "RETURN", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void incrementBookEnchantmentLevel(Inventory inventory, DynamicRegistryManager registryManager, CallbackInfoReturnable<ItemStack> cir, ItemStack itemStack, NbtCompound nbtCompound) {
        if (itemStack.isOf(Items.ENCHANTED_BOOK) && inventory.getStack(0).isOf(ENCHANTMENT_UPGRADE_SMITHING_TEMPLATE)) {
            if (inventory.getStack(2).getCount() == 64) {
                NbtList enchantments = EnchantedBookItem.getEnchantmentNbt(itemStack);
                if (enchantments.size() == 1) {
                    NbtCompound enchantment = enchantments.getCompound(0);
                    if (EnchantmentHelper.getLevelFromNbt(enchantment) < Registries.ENCHANTMENT.get(EnchantmentHelper.getIdFromNbt(enchantment)).getMaxLevel()) {
                        EnchantmentHelper.writeLevelToNbt(enchantment, EnchantmentHelper.getLevelFromNbt(enchantment) + 1);
                    } else cir.setReturnValue(ItemStack.EMPTY);
                } else cir.setReturnValue(ItemStack.EMPTY);
            } else cir.setReturnValue(ItemStack.EMPTY);
        }
        else cir.setReturnValue(itemStack);
    }
}
