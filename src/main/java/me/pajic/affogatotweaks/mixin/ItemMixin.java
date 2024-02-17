package me.pajic.affogatotweaks.mixin;

import de.siphalor.capsaicin.api.food.DynamicFoodPropertiesAccess;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "getUseAction", at = @At("RETURN"), cancellable = true)
    private void cancelUseIfFoodHungerRestorationIsZero(ItemStack stack, CallbackInfoReturnable<UseAction> cir) {
        if (stack.getItem().isFood()) {
            if (DynamicFoodPropertiesAccess.create().withStack(stack).getModifiedFoodComponent().getHunger() == 0) {
                cir.setReturnValue(UseAction.NONE);
            }
            else {
                cir.setReturnValue(UseAction.EAT);
            }
        }
        else {
            cir.setReturnValue(UseAction.NONE);
        }
    }
}
