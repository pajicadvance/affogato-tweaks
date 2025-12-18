package me.pajic.affogato_core.mixin.hunger;

import me.pajic.affogato_core.Main;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(FoodData.class)
public class FoodDataMixin {

    @ModifyArg(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/food/FoodData;addExhaustion(F)V",
                    ordinal = 1
            )
    )
    private float naturalHealingExhaustion(float original) {
        return Main.CONFIG.exhaustionValues.naturalHealing.get();
    }
}
