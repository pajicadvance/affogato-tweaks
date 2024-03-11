package me.pajic.affogatotweaks.mixin.spiceoffabric;

import com.mojang.datafixers.util.Pair;
import de.siphalor.capsaicin.api.food.DynamicFoodPropertiesAccess;
import de.siphalor.spiceoffabric.foodhistory.FoodHistory;
import de.siphalor.spiceoffabric.item.FoodContainerItem;
import de.siphalor.spiceoffabric.util.IndexedValue;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(value = FoodContainerItem.class, remap = false)
public class FoodContainerItemMixin {

    @Shadow @Final private static IndexedValue<ItemStack> NO_STACK;
    @Unique PlayerEntity player;

    @Inject(method = "getNextFoodStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;)Lnet/minecraft/item/ItemStack;", at = @At("HEAD"), locals = LocalCapture.CAPTURE_FAILHARD, remap = true)
    private void getPlayerEntity(ItemStack stack, PlayerEntity player, CallbackInfoReturnable<ItemStack> cir) {
        this.player = player;
    }

    @Inject(method = "findMostAppropriateFood", at = @At("HEAD"), cancellable = true)
    private void replaceFindMostAppropriateFoodMethod(List<IndexedValue<Pair<ItemStack, FoodComponent>>> foods, int requiredFood, CallbackInfoReturnable<IndexedValue<ItemStack>> cir) {
        cir.cancel();
        var bestStack = NO_STACK;
        int currentTimesEaten = 0;
        int bestValue = Integer.MIN_VALUE;
        while (true) {
            for (var value : foods) {
                ItemStack stack = value.value().getFirst();
                int hungerRestorationValue = DynamicFoodPropertiesAccess.create().withStack(stack).getModifiedFoodComponent().getHunger();
                int timesEaten = FoodHistory.get(player).getTimesRecentlyEaten(stack);
                if (timesEaten == currentTimesEaten && hungerRestorationValue > bestValue) {
                    bestStack = new IndexedValue<>(value.index(), stack);
                }
            }
            if (bestStack == NO_STACK) {
                currentTimesEaten++;
            }
            else {
                cir.setReturnValue(bestStack);
                return;
            }
        }
    }
}
