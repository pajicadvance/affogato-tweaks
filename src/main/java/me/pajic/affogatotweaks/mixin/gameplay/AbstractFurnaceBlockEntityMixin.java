package me.pajic.affogatotweaks.mixin.gameplay;

import me.pajic.affogatotweaks.values.MiscValues;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {

    @ModifyConstant(
            method = "getFuel",
            constant = @Constant(intValue = 2400)
    )
    private static int setBlazeRodFuelTime(int constant) {
        return MiscValues.BLAZE_ROD_FUEL_TIME;
    }

    @Redirect(
            method = "getFuel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;add(Ljava/util/Map;Lnet/minecraft/world/level/ItemLike;I)V",
                    ordinal = 27
            )
    )
    private static void removeDriedKelpBlockFuel(Map<Item, Integer> map, ItemLike item, int burnTime) {}

    @Redirect(
            method = "getFuel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;add(Ljava/util/Map;Lnet/minecraft/tags/TagKey;I)V",
                    ordinal = 17
            )
    )
    private static void removeCarpetFuel(Map<Item, Integer> fuelTimes, TagKey<Item> tag, int fuelTime) {}

    @Redirect(
            method = "getFuel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;add(Ljava/util/Map;Lnet/minecraft/tags/TagKey;I)V",
                    ordinal = 14
            )
    )
    private static void removeWoolFuel(Map<Item, Integer> fuelTimes, TagKey<Item> tag, int fuelTime) {}
}
