package me.pajic.affogatotweaks.mixin;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {

    // Increases the amount of items a blaze rod can smelt from 12 to 16
    @ModifyConstant(method = "createFuelTimeMap", constant = @Constant(intValue = 2400))
    private static int setBlazeRodFuelTime(int constant) {
        return 3200;
    }

    // Remove the dried kelp block from fuels
    @Redirect(method = "createFuelTimeMap", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/AbstractFurnaceBlockEntity;addFuel(Ljava/util/Map;Lnet/minecraft/item/ItemConvertible;I)V", ordinal = 27))
    private static void removeDriedKelpBlockFuel(Map<Item, Integer> fuelTimes, ItemConvertible item, int fuelTime) {
    }

    // Remove carpets from fuels
    @Redirect(method = "createFuelTimeMap", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/AbstractFurnaceBlockEntity;addFuel(Ljava/util/Map;Lnet/minecraft/registry/tag/TagKey;I)V", ordinal = 17))
    private static void removeCarpetFuel(Map<Item, Integer> fuelTimes, TagKey<Item> tag, int fuelTime) {
    }

    // Remove wool blocks from fuels
    @Redirect(method = "createFuelTimeMap", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/AbstractFurnaceBlockEntity;addFuel(Ljava/util/Map;Lnet/minecraft/registry/tag/TagKey;I)V", ordinal = 14))
    private static void removeWoolFuel(Map<Item, Integer> fuelTimes, TagKey<Item> tag, int fuelTime) {
    }
}
