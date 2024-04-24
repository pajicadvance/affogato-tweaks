package me.pajic.affogatotweaks.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.RedStoneOreBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(RedStoneOreBlock.class)
public class RedStoneOreBlockMixin {

    // Increases the amount of experience dropped by mining redstone ore
    @ModifyArgs(method = "spawnAfterBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/RedStoneOreBlock;popExperience(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;I)V"))
    private void setRedstoneOreXpDropAmount(Args args) {
        ServerLevel world = args.get(0);
        args.set(2, 3 + world.random.nextInt(5));
    }
}
