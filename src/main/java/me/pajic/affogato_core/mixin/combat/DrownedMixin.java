package me.pajic.affogato_core.mixin.combat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogato_core.Main;
import net.minecraft.world.entity.monster.zombie.Drowned;
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
        return Main.CONFIG.combat.drownedTridentThrowDelay.get();
    }
}