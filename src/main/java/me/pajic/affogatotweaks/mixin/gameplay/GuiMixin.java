package me.pajic.affogatotweaks.mixin.gameplay;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogatotweaks.values.ArmorDefenseValues;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Gui.class)
public class GuiMixin {

    @ModifyExpressionValue(
            method = "renderArmor",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;getArmorValue()I"
            )
    )
    private static int scaleArmorBar(int original) {
        return Math.round(original / ArmorDefenseValues.MAX_ARMOR_MULT);
    }
}
