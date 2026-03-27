package me.pajic.affogato_core.mixin.gameplay;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogato_core.Main;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.FuelValues;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FuelValues.Builder.class)
public class FuelValuesMixin {

    @WrapOperation(
            method = "add(Lnet/minecraft/world/level/ItemLike;I)Lnet/minecraft/world/level/block/entity/FuelValues$Builder;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/entity/FuelValues$Builder;putInternal(ILnet/minecraft/world/item/Item;)V"
            )
    )
    private void setItemBurnTimes(FuelValues.Builder instance, int value, Item item, Operation<Void> original) {
        Integer newValue = Main.CONFIG.furnaceFuelMap.get().get(BuiltInRegistries.ITEM.getKey(item).toString());
        if (newValue != null) {
            if (newValue != 0) original.call(instance, newValue, item);
        }
        else original.call(instance, value, item);
    }

    @WrapOperation(
            method = "lambda$add$0",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/entity/FuelValues$Builder;putInternal(ILnet/minecraft/world/item/Item;)V"
            )
    )
    private void setItemTagBurnTimes(FuelValues.Builder instance, int value, Item item, Operation<Void> original, @Local(argsOnly = true) HolderSet.Named<Item> tag) {
        Integer newValue = Main.CONFIG.furnaceFuelMap.get().get("#" + tag.key().location());
        if (newValue != null) {
            if (newValue != 0) original.call(instance, newValue, item);
        }
        else original.call(instance, value, item);
    }
}
