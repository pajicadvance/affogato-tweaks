package me.pajic.affogatotweaks.mixin;

import de.siphalor.capsaicin.api.food.DynamicFoodPropertiesAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    // Prevents eating food that would not restore any hunger points
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void cancelUseIfFoodHungerRestorationIsZero(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack stack = user.getStackInHand(hand);
        if (stack.getItem().isFood()) {
            if (DynamicFoodPropertiesAccess.create().withStack(stack).getModifiedFoodComponent().getHunger() == 0) {
                cir.setReturnValue(TypedActionResult.fail(stack));
                cir.cancel();
            }
        }
    }
}
