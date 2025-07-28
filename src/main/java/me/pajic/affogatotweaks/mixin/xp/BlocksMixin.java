package me.pajic.affogatotweaks.mixin.xp;

import me.pajic.affogatotweaks.values.XpValues;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Blocks.class)
public class BlocksMixin {

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 0), index = 1)
    private static int setCoalOreXpDropAmount(int max) {
        return XpValues.COAL_MAX;
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 1), index = 1)
    private static int setDeepslateCoalOreXpDropAmount(int max) {
        return XpValues.COAL_MAX;
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 2), index = 1)
    private static int setNetherGoldOreXpDropAmount(int max) {
        return XpValues.NETHER_GOLD_MAX;
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 3))
    private static void setLapisOreXpDropAmount(Args args) {
        args.set(0, XpValues.LAPIS.leftInt());
        args.set(1, XpValues.LAPIS.rightInt());
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 4))
    private static void setDeepslateLapisOreXpDropAmount(Args args) {
        args.set(0, XpValues.LAPIS.leftInt());
        args.set(1, XpValues.LAPIS.rightInt());
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 5))
    private static void setDiamondOreXpDropAmount(Args args) {
        args.set(0, XpValues.DIAMOND.leftInt());
        args.set(1, XpValues.DIAMOND.rightInt());
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 6))
    private static void setDeepslateDiamondOreXpDropAmount(Args args) {
        args.set(0, XpValues.DIAMOND.leftInt());
        args.set(1, XpValues.DIAMOND.rightInt());
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 7))
    private static void setEmeraldOreXpDropAmount(Args args) {
        args.set(0, XpValues.EMERALD.leftInt());
        args.set(1, XpValues.EMERALD.rightInt());
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 8))
    private static void setDeepslateEmeraldOreXpDropAmount(Args args) {
        args.set(0, XpValues.EMERALD.leftInt());
        args.set(1, XpValues.EMERALD.rightInt());
    }

    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;", ordinal = 9))
    private static void setQuartzOreXpDropAmount(Args args) {
        args.set(0, XpValues.QUARTZ.leftInt());
        args.set(1, XpValues.QUARTZ.rightInt());
    }
}
