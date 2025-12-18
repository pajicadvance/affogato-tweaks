package me.pajic.affogato_core.mixin.gameplay;

import me.pajic.affogato_core.Main;
import net.minecraft.world.item.enchantment.effects.ReplaceDisk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ReplaceDisk.class)
public class ReplaceDiskMixin {

    @ModifyArg(
            method = "apply",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/Mth;square(I)I"
            )
    )
    private int triggerFrostWalkerEarly(int i) {
        return Main.CONFIG.misc.frostWalkerEarlyTrigger.get() ? i - 1 : i;
    }
}
