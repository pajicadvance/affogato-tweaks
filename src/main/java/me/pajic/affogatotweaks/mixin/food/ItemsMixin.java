package me.pajic.affogatotweaks.mixin.food;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.pajic.affogatotweaks.values.NutritionValues;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Items.class)
public class ItemsMixin {

    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/food/Foods;OMINOUS_BOTTLE:Lnet/minecraft/world/food/FoodProperties;"
            )
    )
    private static FoodProperties ominousBottleNoFoodValues(FoodProperties original) {
        return new FoodProperties.Builder()
                .nutrition(NutritionValues.OMINOUS_BOTTLE.leftInt())
                .saturationModifier(NutritionValues.OMINOUS_BOTTLE.rightFloat())
                .build();
    }

    @WrapOperation(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/Item$Properties;food(Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/Item$Properties;"
            )
    )
    private static Item.Properties goldenItemsNotEdible(Item.Properties instance, FoodProperties food, Operation<Item.Properties> original) {
        return NutritionValues.REMOVED_FOODS.contains(food) ? instance : original.call(instance, food);
    }
}
