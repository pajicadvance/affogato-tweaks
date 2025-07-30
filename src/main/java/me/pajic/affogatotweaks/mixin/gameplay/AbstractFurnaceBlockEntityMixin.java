package me.pajic.affogatotweaks.mixin.gameplay;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import me.pajic.affogatotweaks.values.MiscValues;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {

    @Definition(id = "BLAZE_ROD", field = "Lnet/minecraft/world/item/Items;BLAZE_ROD:Lnet/minecraft/world/item/Item;")
    @Expression("?(?, BLAZE_ROD, @(?))")
    @ModifyExpressionValue(
            method = "getFuel",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static int setBlazeRodFuelTime(int constant) {
        return MiscValues.BLAZE_ROD_FUEL_TIME;
    }

    @Definition(id = "DRIED_KELP_BLOCK", field = "Lnet/minecraft/world/level/block/Blocks;DRIED_KELP_BLOCK:Lnet/minecraft/world/level/block/Block;")
    @Expression("?(?, DRIED_KELP_BLOCK, ?)")
    @WrapWithCondition(
            method = "getFuel",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static boolean removeDriedKelpBlockFuel(Map<Item, Integer> map, ItemLike item, int burnTime) {
        return false;
    }

    @Definition(id = "WOOL_CARPETS", field = "Lnet/minecraft/tags/ItemTags;WOOL_CARPETS:Lnet/minecraft/tags/TagKey;")
    @Expression("?(?, WOOL_CARPETS, ?)")
    @WrapWithCondition(
            method = "getFuel",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static boolean removeCarpetFuel(Map<Item, Integer> fuelTimes, TagKey<Item> tag, int fuelTime) {
        return false;
    }

    @Definition(id = "WOOL", field = "Lnet/minecraft/tags/ItemTags;WOOL:Lnet/minecraft/tags/TagKey;")
    @Expression("?(?, WOOL, ?)")
    @WrapWithCondition(
            method = "getFuel",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static boolean removeWoolFuel(Map<Item, Integer> fuelTimes, TagKey<Item> tag, int fuelTime) {
        return false;
    }
}
