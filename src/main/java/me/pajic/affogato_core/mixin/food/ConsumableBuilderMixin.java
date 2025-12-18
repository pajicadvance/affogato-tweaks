package me.pajic.affogato_core.mixin.food;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogato_core.Main;
import net.minecraft.world.item.component.Consumable;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Consumable.Builder.class)
public class ConsumableBuilderMixin {

    @ModifyExpressionValue(
            method = "build",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/item/component/Consumable$Builder;consumeSeconds:F",
                    opcode = Opcodes.GETFIELD
            )
    )
    private float modifyEatSpeed(float original) {
        return original + Main.CONFIG.combat.eatTimeIncrease.get();
    }
}
