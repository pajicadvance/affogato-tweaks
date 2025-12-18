package me.pajic.affogato_core.mixin.food;

import me.pajic.affogato_core.Main;
import net.minecraft.world.level.block.CakeBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(CakeBlock.class)
public class CakeBlockMixin {

    @ModifyArgs(
            method = "eat",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/food/FoodData;eat(IF)V"
            )
    )
    private static void buffCake(Args args) {
        args.set(0, Main.CONFIG.misc.cakeSliceFoodLevel.get());
        args.set(1, Main.CONFIG.misc.cakeSliceSaturationLevel.get());
    }
}
