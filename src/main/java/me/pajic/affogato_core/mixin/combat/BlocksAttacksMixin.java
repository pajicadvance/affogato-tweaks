package me.pajic.affogato_core.mixin.combat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogato_core.Main;
import net.minecraft.world.item.component.BlocksAttacks;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlocksAttacks.class)
public class BlocksAttacksMixin {

    @Mixin(BlocksAttacks.ItemDamageFunction.class)
    public static class ItemDamageFunctionMixin {

        @ModifyExpressionValue(
                method = "apply",
                at = @At(
                        value = "FIELD",
                        target = "Lnet/minecraft/world/item/component/BlocksAttacks$ItemDamageFunction;threshold:F",
                        opcode = Opcodes.GETFIELD
                )
        )
        private float reduceBlockThreshold(float original) {
            return Main.CONFIG.combat.shieldBlockDamageThreshold.get();
        }
    }
}
