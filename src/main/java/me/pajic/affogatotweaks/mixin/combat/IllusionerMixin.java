package me.pajic.affogatotweaks.mixin.combat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogatotweaks.values.MobValues;
import net.minecraft.world.entity.monster.Illusioner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Illusioner.class)
public class IllusionerMixin {

    @ModifyExpressionValue(
            method = "createAttributes",
            at = @At(
                    value = "CONSTANT",
                    args = "doubleValue=32.0"
            )
    )
    private static double modifyFollowRange(double original) {
        return MobValues.ILLUSIONER_FOLLOW_RANGE;
    }
}
