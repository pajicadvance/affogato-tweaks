package me.pajic.affogatotweaks.mixin.difficulty;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldCreationUiState.class)
public class WorldCreationUiStateMixin {

    @ModifyExpressionValue(
            method = "<init>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/Difficulty;NORMAL:Lnet/minecraft/world/Difficulty;"
            )
    )
    private Difficulty setDefaultDifficulty(Difficulty original) {
        return Difficulty.HARD;
    }
}