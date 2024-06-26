package me.pajic.affogatotweaks.mixin;

import net.minecraft.client.gui.screens.worldselection.WorldCreationContext;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.file.Path;
import java.util.Optional;
import java.util.OptionalLong;

@Mixin(WorldCreationUiState.class)
public class WorldCreationUiStateMixin {

    @Shadow private Difficulty difficulty;

    // Changes the default difficulty set in the world creation screen to Hard
    @Inject(method = "<init>", at = @At("TAIL"))
    private void setDefaultDifficulty(Path savesDirectory, WorldCreationContext generatorOptionsHolder, Optional defaultWorldType, OptionalLong seed, CallbackInfo ci) {
        difficulty = Difficulty.HARD;
    }
}
