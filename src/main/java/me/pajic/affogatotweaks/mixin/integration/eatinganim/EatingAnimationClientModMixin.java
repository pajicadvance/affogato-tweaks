package me.pajic.affogatotweaks.mixin.integration.eatinganim;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import ru.tpsd.eatinganimationmod.EatingAnimationClientMod;

@IfModLoaded("eatinganimationid")
@Mixin(EatingAnimationClientMod.class)
public class EatingAnimationClientModMixin {

    @ModifyExpressionValue(
            method = "lambda$onInitializeClient$1",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=30.0"
            )
    )
    private static float fixEatingAnimation(float original, @Local(argsOnly = true) ItemStack stack, @Local(argsOnly = true) LivingEntity livingEntity) {
        return stack.getUseDuration(livingEntity);
    }
}
