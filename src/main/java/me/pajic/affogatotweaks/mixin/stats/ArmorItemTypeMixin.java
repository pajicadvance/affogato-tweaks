package me.pajic.affogatotweaks.mixin.stats;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.pajic.affogatotweaks.values.DurabilityValues;
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

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void setBaseDurabilities(String string, int ordinal, EquipmentSlot slot, int dur, String name, CallbackInfo ci) {
        switch (ordinal) {
            case 0: {
                durability = DurabilityValues.HELMET_BASE;
                break;
            }
            case 1, 4: {
                durability = DurabilityValues.BODY_BASE;
                break;
            }
            case 2: {
                durability = DurabilityValues.LEGGINGS_BASE;
                break;
            }
            case 3: {
                durability = DurabilityValues.BOOTS_BASE;
                break;
            }
        }
    }

    @WrapMethod(method = "getDurability")
    private int modifyDurabilityMultipliers(int durabilityFactor, Operation<Integer> original) {
        return switch (durabilityFactor) {
            case 4 -> original.call(DurabilityValues.WOLF_ARMOR_MULT);
            case 5 -> original.call(DurabilityValues.LEATHER_ARMOR_MULT);
            case 7 -> original.call(DurabilityValues.GOLDEN_ARMOR_MULT);
            case 15 -> original.call(DurabilityValues.IRON_ARMOR_MULT);
            case 25 -> original.call(DurabilityValues.TURTLE_ARMOR_MULT);
            case 33 -> original.call(DurabilityValues.DIAMOND_ARMOR_MULT);
            case 37 -> original.call(DurabilityValues.NETHERITE_ARMOR_MULT);
            default -> original.call(durabilityFactor);
        };
    }
}
