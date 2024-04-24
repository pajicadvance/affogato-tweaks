package me.pajic.affogatotweaks.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.pajic.affogatotweaks.item.ModItems.ENCHANTMENT_UPGRADE_SMITHING_TEMPLATE;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.ItemStack;

@Mixin(SmithingMenu.class)
public abstract class SmithingMenuMixin extends ItemCombinerMenu {

    public SmithingMenuMixin(@Nullable MenuType<?> type, int syncId, Inventory playerInventory, ContainerLevelAccess context) {
        super(type, syncId, playerInventory, context);
    }

    // Decrements the material by 64 instead of 1 if the enchantment upgrade recipe is used
    @Inject(method = "shrinkStackInSlot", at = @At("HEAD"), cancellable = true)
    private void modifyDecrementAmount(int slot, CallbackInfo ci) {
        if (slot == 2 && inputSlots.getItem(0).is(ENCHANTMENT_UPGRADE_SMITHING_TEMPLATE)) {
            ci.cancel();
            ItemStack itemStack = inputSlots.getItem(slot);
            if (!itemStack.isEmpty()) {
                itemStack.shrink(64);
                inputSlots.setItem(slot, itemStack);
            }
        }
    }
}
