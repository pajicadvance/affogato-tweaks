package me.pajic.affogatotweaks.mixin.combat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public class PlayerMixin {

    @ModifyExpressionValue(
            method = "hurtCurrentlyUsedShield",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=3.0"
            )
    )
    private float reduceBlockThreshold(float original) {
        return 1.0F;
    }
}
