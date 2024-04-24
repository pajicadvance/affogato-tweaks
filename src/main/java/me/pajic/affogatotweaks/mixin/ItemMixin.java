package me.pajic.affogatotweaks.mixin;

import de.siphalor.capsaicin.api.food.DynamicFoodPropertiesAccess;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    // Prevents eating food that would not restore any hunger points
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void cancelUseIfFoodHungerRestorationIsZero(Level world, Player user, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack stack = user.getItemInHand(hand);
        if (stack.getItem().isEdible()) {
            if (DynamicFoodPropertiesAccess.create().withStack(stack).getModifiedFoodComponent().getNutrition() == 0) {
                cir.setReturnValue(InteractionResultHolder.fail(stack));
                cir.cancel();
            }
        }
    }
}
