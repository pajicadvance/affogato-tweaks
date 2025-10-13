package me.pajic.affogatotweaks.mixin.combat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.pajic.affogatotweaks.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Mob.class)
public class MobMixin {

    @ModifyArg(
            method = "populateDefaultEquipmentSlots",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/RandomSource;nextInt(I)I"
            )
    )
    private int increaseBaseChance(int bound) {
        return 3;
    }

    @ModifyExpressionValue(
            method = "populateDefaultEquipmentSlots",
            at = @At(
                    value = "CONSTANT",
                    args = "floatValue=0.095"
            )
    )
    private float increaseIncrementChance(float original) {
        return 0.1087F;
    }

    @WrapMethod(method = "getEquipmentForSlot")
    private static Item expandArmorChoices(EquipmentSlot slot, int chance, Operation<Item> original) {
        switch (slot) {
            case HEAD:
                if (chance == 0) {
                    return Items.LEATHER_HELMET;
                } else if (chance == 1) {
                    return ModItems.COPPER_HELMET;
                } else if (chance == 2) {
                    return Items.GOLDEN_HELMET;
                } else if (chance == 3) {
                    return Items.CHAINMAIL_HELMET;
                } else if (chance == 4) {
                    return Items.IRON_HELMET;
                } else if (chance == 5) {
                    return Items.DIAMOND_HELMET;
                }
            case CHEST:
                if (chance == 0) {
                    return Items.LEATHER_CHESTPLATE;
                } else if (chance == 1) {
                    return ModItems.COPPER_CHESTPLATE;
                } else if (chance == 2) {
                    return Items.GOLDEN_CHESTPLATE;
                } else if (chance == 3) {
                    return Items.CHAINMAIL_CHESTPLATE;
                } else if (chance == 4) {
                    return Items.IRON_CHESTPLATE;
                } else if (chance == 5) {
                    return Items.DIAMOND_CHESTPLATE;
                }
            case LEGS:
                if (chance == 0) {
                    return Items.LEATHER_LEGGINGS;
                } else if (chance == 1) {
                    return ModItems.COPPER_LEGGINGS;
                } else if (chance == 2) {
                    return Items.GOLDEN_LEGGINGS;
                } else if (chance == 3) {
                    return Items.CHAINMAIL_LEGGINGS;
                } else if (chance == 4) {
                    return Items.IRON_LEGGINGS;
                } else if (chance == 5) {
                    return Items.DIAMOND_LEGGINGS;
                }
            case FEET:
                if (chance == 0) {
                    return Items.LEATHER_BOOTS;
                } else if (chance == 1) {
                    return ModItems.COPPER_BOOTS;
                } else if (chance == 2) {
                    return Items.GOLDEN_BOOTS;
                } else if (chance == 3) {
                    return Items.CHAINMAIL_BOOTS;
                } else if (chance == 4) {
                    return Items.IRON_BOOTS;
                } else if (chance == 5) {
                    return Items.DIAMOND_BOOTS;
                }
            default:
                return null;
        }
    }
}
