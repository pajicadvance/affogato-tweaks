package me.pajic.affogatotweaks.mixin;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SmithingScreenHandler;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.pajic.affogatotweaks.item.ModItems.ENCHANTMENT_UPGRADE_SMITHING_TEMPLATE;

@Mixin(SmithingScreenHandler.class)
public abstract class SmithingScreenHandlerMixin extends ForgingScreenHandler {

    public SmithingScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    // Decrements the material by 64 instead of 1 if the enchantment upgrade recipe is used
    @Inject(method = "decrementStack", at = @At("HEAD"), cancellable = true)
    private void modifyDecrementAmount(int slot, CallbackInfo ci) {
        if (slot == 2 && input.getStack(0).isOf(ENCHANTMENT_UPGRADE_SMITHING_TEMPLATE)) {
            ci.cancel();
            ItemStack itemStack = input.getStack(slot);
            if (!itemStack.isEmpty()) {
                itemStack.decrement(64);
                input.setStack(slot, itemStack);
            }
        }
    }
}
