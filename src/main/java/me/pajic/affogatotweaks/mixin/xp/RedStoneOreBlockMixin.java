package me.pajic.affogatotweaks.mixin.xp;

import net.minecraft.world.level.block.RedStoneOreBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(RedStoneOreBlock.class)
public class RedStoneOreBlockMixin {

    @ModifyArgs(
            method = "spawnAfterBreak",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;"
            )
    )
    private void setRedstoneOreXpDropAmount(Args args) {
        args.set(0, 3);
        args.set(1, 7);
    }
}