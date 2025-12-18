package me.pajic.affogato_core.mixin.food;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogato_core.Main;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FoodData.class)
public class FoodDataMixin {

    @ModifyExpressionValue(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerPlayer;isHurt()Z",
                    ordinal = 0
            )
    )
    private boolean noRapidHealing(boolean original) {
        return !Main.CONFIG.misc.noRapidHealing.get();
    }
}
