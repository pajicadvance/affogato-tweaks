package me.pajic.affogatotweaks.mixin.xp;

import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Blocks.class)
public class BlocksMixin {

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 0), index = 1)
    private static int setCoalOreXpDropAmount(int min) {
        return 3;
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 1), index = 1)
    private static int setDeepslateCoalOreXpDropAmount(int min) {
        return 3;
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 2), index = 1)
    private static int setNetherGoldOreXpDropAmount(int min) {
        return 3;
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 3))
    private static void setLapisOreXpDropAmount(Args args) {
        args.set(0, 3);
        args.set(1, 7);
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 4))
    private static void setDeepslateLapisOreXpDropAmount(Args args) {
        args.set(0, 3);
        args.set(1, 7);
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 5))
    private static void setDiamondOreXpDropAmount(Args args) {
        args.set(0, 7);
        args.set(1, 10);
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 6))
    private static void setDeepslateDiamondOreXpDropAmount(Args args) {
        args.set(0, 7);
        args.set(1, 10);
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 7))
    private static void setEmeraldOreXpDropAmount(Args args) {
        args.set(0, 12);
        args.set(1, 15);
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 8))
    private static void setDeepslateEmeraldOreXpDropAmount(Args args) {
        args.set(0, 12);
        args.set(1, 15);
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 9))
    private static void setQuartzOreXpDropAmount(Args args) {
        args.set(0, 3);
        args.set(1, 7);
    }
}
