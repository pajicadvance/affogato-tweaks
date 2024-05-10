package me.pajic.affogatotweaks.mixin.spiceoffabric;

import com.mojang.datafixers.util.Pair;
import de.siphalor.capsaicin.api.food.DynamicFoodPropertiesAccess;
import de.siphalor.spiceoffabric.foodhistory.FoodHistory;
import de.siphalor.spiceoffabric.item.FoodContainerItem;
import de.siphalor.spiceoffabric.util.IndexedValue;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;

@Mixin(value = FoodContainerItem.class, remap = false)
public class FoodContainerItemMixin {

    @Shadow @Final private static IndexedValue<ItemStack> NO_STACK;
    @Unique Player player;

    @Inject(method = "getNextFoodStack(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/item/ItemStack;", at = @At("HEAD"), locals = LocalCapture.CAPTURE_FAILHARD, remap = true)
    private void getPlayerEntity(ItemStack stack, Player player, CallbackInfoReturnable<ItemStack> cir) {
        this.player = player;
    }

    @Inject(method = "findMostAppropriateFood", at = @At("HEAD"), cancellable = true)
    private void replaceFindMostAppropriateFoodMethod(List<IndexedValue<Pair<ItemStack, FoodProperties>>> foods, int requiredFood, CallbackInfoReturnable<IndexedValue<ItemStack>> cir) {
        cir.cancel();
        var bestStack = NO_STACK;
        int currentTimesEaten = 0;
        int bestValue = Integer.MIN_VALUE;
        while (true) {
            for (var value : foods) {
                ItemStack stack = value.value().getFirst();
                int hungerRestorationValue = DynamicFoodPropertiesAccess.create().withStack(stack).getModifiedFoodComponent().getNutrition();
                int timesEaten = FoodHistory.get(player).getTimesRecentlyEaten(stack);
                if (timesEaten == currentTimesEaten && hungerRestorationValue > bestValue) {
                    bestValue = hungerRestorationValue;
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
