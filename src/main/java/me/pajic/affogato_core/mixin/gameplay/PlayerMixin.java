package me.pajic.affogato_core.mixin.gameplay;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogato_core.Main;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public class PlayerMixin {

    @ModifyExpressionValue(
            method = "hasCorrectToolForDrops",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;requiresCorrectToolForDrops()Z"
            )
    )
    private boolean requireAxeForLogs(boolean original, @Local(argsOnly = true) BlockState blockState) {
        return Main.CONFIG.features.affogatoEarlyGameChanges.get() && blockState.is(BlockTags.LOGS) || original;
    }
}
