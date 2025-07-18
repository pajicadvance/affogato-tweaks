package me.pajic.affogatotweaks.mixin.food;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.food.FoodProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FoodProperties.Builder.class)
public class FoodPropertiesBuilderMixin {

    @ModifyExpressionValue(
            method = "build",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/food/FoodProperties$Builder;eatSeconds:F"
            )
    )
    private float modifyEatSpeed(float original) {
        // normal speed 3.2
        // fast speed 2.4
        return original + 1.6F;
    }
}
