package me.pajic.affogatotweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {

    // Increases the amount of items a blaze rod can smelt from 12 to 16
    @ModifyConstant(method = "getFuel", constant = @Constant(intValue = 2400))
    private static int setBlazeRodFuelTime(int constant) {
        return 3200;
    }

    // Remove the dried kelp block from fuels
    @Redirect(method = "getFuel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;add(Ljava/util/Map;Lnet/minecraft/world/level/ItemLike;I)V", ordinal = 27))
    private static void removeDriedKelpBlockFuel(Map<Item, Integer> map, ItemLike item, int burnTime) {
    }

    // Remove carpets from fuels
    @Redirect(method = "getFuel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;add(Ljava/util/Map;Lnet/minecraft/tags/TagKey;I)V", ordinal = 17))
    private static void removeCarpetFuel(Map<Item, Integer> fuelTimes, TagKey<Item> tag, int fuelTime) {
    }

    // Remove wool blocks from fuels
    @Redirect(method = "getFuel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;add(Ljava/util/Map;Lnet/minecraft/tags/TagKey;I)V", ordinal = 14))
    private static void removeWoolFuel(Map<Item, Integer> fuelTimes, TagKey<Item> tag, int fuelTime) {
    }
}
