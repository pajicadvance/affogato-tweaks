package me.pajic.affogatotweaks.mixin.combat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogatotweaks.values.MobValues;
import net.minecraft.world.entity.monster.Drowned;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Drowned.class)
public class DrownedMixin {

    @ModifyExpressionValue(
            method = "addBehaviourGoals",
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=40"
            )
    )
    private int setDrownedTridentFireRate(int original) {
        return MobValues.DROWNED_TRIDENT_THROW_DELAY;
    }
}