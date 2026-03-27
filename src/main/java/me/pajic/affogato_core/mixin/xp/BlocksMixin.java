package me.pajic.affogato_core.mixin.xp;

import me.pajic.affogato_core.Main;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Blocks.class)
public class BlocksMixin {

    @ModifyArgs(
            method = "lambda$static$23",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;"
            )
    )
    private static void setCoalOreXpDropAmount(Args args) {
        args.set(0, Main.CONFIG.experience.coal.get().component1());
        args.set(1, Main.CONFIG.experience.coal.get().component2());
    }

    @ModifyArgs(
            method = "lambda$static$24",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;"
            )
    )
    private static void setDeepslateCoalOreXpDropAmount(Args args) {
        args.set(0, Main.CONFIG.experience.coal.get().component1());
        args.set(1, Main.CONFIG.experience.coal.get().component2());
    }

    @ModifyArgs(
            method = "lambda$static$25",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;"
            )
    )
    private static void setNetherGoldOreXpDropAmount(Args args) {
        args.set(0, Main.CONFIG.experience.netherGold.get().component1());
        args.set(1, Main.CONFIG.experience.netherGold.get().component2());
    }

    @ModifyArgs(
            method = "lambda$static$37",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;"
            )
    )
    private static void setLapisOreXpDropAmount(Args args) {
        args.set(0, Main.CONFIG.experience.lapis.get().component1());
        args.set(1, Main.CONFIG.experience.lapis.get().component2());
    }

    @ModifyArgs(
            method = "lambda$static$38",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;"
            )
    )
    private static void setDeepslateLapisOreXpDropAmount(Args args) {
        args.set(0, Main.CONFIG.experience.lapis.get().component1());
        args.set(1, Main.CONFIG.experience.lapis.get().component2());
    }

    @ModifyArgs(
            method = "lambda$static$67",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;"
            )
    )
    private static void setDiamondOreXpDropAmount(Args args) {
        args.set(0, Main.CONFIG.experience.diamond.get().component1());
        args.set(1, Main.CONFIG.experience.diamond.get().component2());
    }

    @ModifyArgs(
            method = "lambda$static$68",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;"
            )
    )
    private static void setDeepslateDiamondOreXpDropAmount(Args args) {
        args.set(0, Main.CONFIG.experience.diamond.get().component1());
        args.set(1, Main.CONFIG.experience.diamond.get().component2());
    }

    @ModifyArgs(
            method = "lambda$static$174",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;"
            )
    )
    private static void setEmeraldOreXpDropAmount(Args args) {
        args.set(0, Main.CONFIG.experience.emerald.get().component1());
        args.set(1, Main.CONFIG.experience.emerald.get().component2());
    }

    @ModifyArgs(
            method = "lambda$static$175",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;"
            )
    )
    private static void setDeepslateEmeraldOreXpDropAmount(Args args) {
        args.set(0, Main.CONFIG.experience.emerald.get().component1());
        args.set(1, Main.CONFIG.experience.emerald.get().component2());
    }

    @ModifyArgs(
            method = "lambda$static$231",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/valueproviders/UniformInt;of(II)Lnet/minecraft/util/valueproviders/UniformInt;"
            )
    )
    private static void setQuartzOreXpDropAmount(Args args) {
        args.set(0, Main.CONFIG.experience.quartz.get().component1());
        args.set(1, Main.CONFIG.experience.quartz.get().component2());
    }
}
