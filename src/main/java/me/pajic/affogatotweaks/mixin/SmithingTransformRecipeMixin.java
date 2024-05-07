package me.pajic.affogatotweaks.mixin;

import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static me.pajic.affogatotweaks.item.ModItems.ENCHANTMENT_UPGRADE_SMITHING_TEMPLATE;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.Container;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

@Mixin(SmithingTransformRecipe.class)
public class SmithingTransformRecipeMixin {

    // Defines conditions under which the enchantment upgrade recipe can be used and increments enchanted book enchantment level
    @Inject(method = "assemble", at = @At(value = "RETURN", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void incrementBookEnchantmentLevel(Container inventory, RegistryAccess registryManager, CallbackInfoReturnable<ItemStack> cir, ItemStack itemStack, CompoundTag nbtCompound) {
        if (itemStack.is(Items.ENCHANTED_BOOK) && inventory.getItem(0).is(ENCHANTMENT_UPGRADE_SMITHING_TEMPLATE)) {
            ListTag enchantments = EnchantedBookItem.getEnchantments(itemStack);
            if (enchantments.size() == 1) {
                CompoundTag enchantmentCompound = enchantments.getCompound(0);
                Enchantment enchantment = BuiltInRegistries.ENCHANTMENT.get(EnchantmentHelper.getEnchantmentId(enchantmentCompound));
                int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(enchantmentCompound);

                if (enchantmentLevel < enchantment.getMaxLevel() && inventory.getItem(2).getCount() >= 8 / (int) Math.pow(2, enchantment.getMaxLevel() - enchantmentLevel - 1)) {
                    EnchantmentHelper.setEnchantmentLevel(enchantmentCompound, EnchantmentHelper.getEnchantmentLevel(enchantmentCompound) + 1);
                } else cir.setReturnValue(ItemStack.EMPTY);
            } else cir.setReturnValue(ItemStack.EMPTY);
        }
        else cir.setReturnValue(itemStack);
    }
}
