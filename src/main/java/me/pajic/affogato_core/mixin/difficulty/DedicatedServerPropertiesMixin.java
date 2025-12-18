package me.pajic.affogato_core.mixin.difficulty;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogato_core.Main;
import net.minecraft.server.dedicated.DedicatedServerProperties;
import net.minecraft.world.Difficulty;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DedicatedServerProperties.class)
public class DedicatedServerPropertiesMixin {

    @ModifyExpressionValue(
            method = "<init>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/Difficulty;EASY:Lnet/minecraft/world/Difficulty;",
                    opcode = Opcodes.GETSTATIC
            )
    )
    private Difficulty setDefaultDifficulty(Difficulty original) {
        return Main.CONFIG.features.hardDifficultyDefault.get() ? Difficulty.HARD : original;
    }
}
