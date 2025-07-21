package me.pajic.affogatotweaks.mixin.worldgen;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.level.levelgen.structure.structures.IglooPieces;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(IglooPieces.class)
public class IglooPiecesMixin {

    @ModifyExpressionValue(
            method = "addPieces",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/RandomSource;nextDouble()D"
            )
    )
    private static double nukeIglooBasement(double original) {
        return 1;
    }
}
