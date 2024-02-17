package me.pajic.affogatotweaks.mixin;

import de.siphalor.capsaicin.api.food.DynamicFoodPropertiesAccess;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "getUseAction", at = @At("RETURN"), cancellable = true)
    private void cancelUseIfFoodHungerRestorationIsZero1(ItemStack stack, CallbackInfoReturnable<UseAction> cir) {
        cir.setReturnValue(stack.getItem().isFood() && DynamicFoodPropertiesAccess.create().withStack(stack).getModifiedFoodComponent().getHunger() != 0 ? UseAction.EAT : UseAction.NONE);
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void cancelUseIfFoodHungerRestorationIsZero2(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack stack = user.getStackInHand(hand);
        if (stack.getItem().isFood()) {
            if (DynamicFoodPropertiesAccess.create().withStack(stack).getModifiedFoodComponent().getHunger() == 0) {
                cir.setReturnValue(TypedActionResult.fail(stack));
                cir.cancel();
            }
        }
    }

    @Inject(method = "finishUsing", at = @At("RETURN"), cancellable = true)
    private void cancelUseIfFoodHungerRestorationIsZero3(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        cir.setReturnValue(stack.getItem().isFood() && DynamicFoodPropertiesAccess.create().withStack(stack).getModifiedFoodComponent().getHunger() != 0 ? user.eatFood(world, stack) : stack);
    }
}
