package me.pajic.affogatotweaks.mixin;

import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(RedstoneOreBlock.class)
public class RedstoneOreBlockMixin {

    @ModifyArgs(method = "onStacksDropped", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/RedstoneOreBlock;dropExperience(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;I)V"))
    private void setRedstoneOreXpDropAmount(Args args) {
        ServerWorld world = args.get(0);
        args.set(2, 3 + world.random.nextInt(4));
    }
}
