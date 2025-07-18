package me.pajic.affogatotweaks.mixin.stats;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorItem.Type.class)
public class ArmorItemTypeMixin {

    @Shadow @Mutable @Final private int durability;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void setBaseDurabilities(String string, int ordinal, EquipmentSlot slot, int dur, String name, CallbackInfo ci) {
        switch (ordinal) {
            case 0: {
                durability = 13;
                break;
            }
            case 1, 4: {
                durability = 16;
                break;
            }
            case 2: {
                durability = 15;
                break;
            }
            case 3: {
                durability = 12;
                break;
            }
        }
    }

    @WrapMethod(method = "getDurability")
    private int modifyDurabilityMultipliers(int durabilityFactor, Operation<Integer> original) {
        return switch (durabilityFactor) {
            case 4 -> original.call(8); // wolf armor
            case 5 -> original.call(10); // leather
            case 7 -> original.call(5); // gold
            case 15 -> original.call(10); // iron, chain
            case 25 -> original.call(15); // turtle
            case 33 -> original.call(20); // diamond
            case 37 -> original.call(50); // netherite
            default -> original.call(durabilityFactor);
        };
    }
}
