package me.pajic.affogatotweaks.mixin.difficulty;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.server.dedicated.DedicatedServerProperties;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DedicatedServerProperties.class)
public class DedicatedServerPropertiesMixin {

    @ModifyExpressionValue(
            method = "<init>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/Difficulty;EASY:Lnet/minecraft/world/Difficulty;"
            )
    )
    private Difficulty setDefaultDifficulty(Difficulty original) {
        return Difficulty.HARD;
    }
}
