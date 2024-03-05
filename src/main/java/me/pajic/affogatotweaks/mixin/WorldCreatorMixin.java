package me.pajic.affogatotweaks.mixin;

import net.minecraft.client.gui.screen.world.WorldCreator;
import net.minecraft.client.world.GeneratorOptionsHolder;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.file.Path;
import java.util.Optional;
import java.util.OptionalLong;

@Mixin(WorldCreator.class)
public class WorldCreatorMixin {

    @Shadow private Difficulty difficulty;

    // Changes the default difficulty set in the world creation screen to Hard
    @Inject(method = "<init>", at = @At("TAIL"))
    private void setDefaultDifficulty(Path savesDirectory, GeneratorOptionsHolder generatorOptionsHolder, Optional defaultWorldType, OptionalLong seed, CallbackInfo ci) {
        difficulty = Difficulty.HARD;
    }
}
