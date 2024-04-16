package me.pajic.affogatotweaks.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Blocks.class)
public class BlocksMixin {

    // Decreases torch light level from 14 to 10
    @Inject(method = "method_26152", at = @At("RETURN"), cancellable = true)
    private static void setTorchLightLevel(BlockState state, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(11);
    }

    @Inject(method = "method_26136", at = @At("RETURN"), cancellable = true)
    private static void setWallTorchLightLevel(BlockState state, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(11);
    }

    // Decreases slipperiness of ice blocks to nerf boat speed on ice
    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 0.98F))
    private static float setIceSlipperiness(float constant) {
        return 0.965F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 0.989F))
    private static float setBlueIceSlipperiness(float constant) {
        return 0.97F;
    }

    // Modifies the amount of experience dropped from mining ore
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/intprovider/UniformIntProvider;create(II)Lnet/minecraft/util/math/intprovider/UniformIntProvider;", ordinal = 0), index = 1)
    private static int setCoalOreXpDropAmount(int min) {
        return 3;
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/intprovider/UniformIntProvider;create(II)Lnet/minecraft/util/math/intprovider/UniformIntProvider;", ordinal = 1), index = 1)
    private static int setDeepslateCoalOreXpDropAmount(int min) {
        return 3;
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/intprovider/UniformIntProvider;create(II)Lnet/minecraft/util/math/intprovider/UniformIntProvider;", ordinal = 2), index = 1)
    private static int setNetherGoldOreXpDropAmount(int min) {
        return 3;
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/intprovider/UniformIntProvider;create(II)Lnet/minecraft/util/math/intprovider/UniformIntProvider;", ordinal = 3))
    private static void setLapisOreXpDropAmount(Args args) {
        args.set(0, 3);
        args.set(1, 7);
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/intprovider/UniformIntProvider;create(II)Lnet/minecraft/util/math/intprovider/UniformIntProvider;", ordinal = 4))
    private static void setDeepslateLapisOreXpDropAmount(Args args) {
        args.set(0, 3);
        args.set(1, 7);
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/intprovider/UniformIntProvider;create(II)Lnet/minecraft/util/math/intprovider/UniformIntProvider;", ordinal = 5))
    private static void setDiamondOreXpDropAmount(Args args) {
        args.set(0, 7);
        args.set(1, 10);
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/intprovider/UniformIntProvider;create(II)Lnet/minecraft/util/math/intprovider/UniformIntProvider;", ordinal = 6))
    private static void setDeepslateDiamondOreXpDropAmount(Args args) {
        args.set(0, 7);
        args.set(1, 10);
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/intprovider/UniformIntProvider;create(II)Lnet/minecraft/util/math/intprovider/UniformIntProvider;", ordinal = 7))
    private static void setEmeraldOreXpDropAmount(Args args) {
        args.set(0, 7);
        args.set(1, 10);
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/intprovider/UniformIntProvider;create(II)Lnet/minecraft/util/math/intprovider/UniformIntProvider;", ordinal = 8))
    private static void setDeepslateEmeraldOreXpDropAmount(Args args) {
        args.set(0, 7);
        args.set(1, 10);
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/intprovider/UniformIntProvider;create(II)Lnet/minecraft/util/math/intprovider/UniformIntProvider;", ordinal = 9))
    private static void setQuartzOreXpDropAmount(Args args) {
        args.set(0, 3);
        args.set(1, 7);
    }
}
