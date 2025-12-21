package me.pajic.affogato_core.mixin.gameplay;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.pajic.affogato_core.Main;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MapItem.class)
public class MapItemMixin {

    @WrapMethod(method = "inventoryTick")
    private void alwaysUpdateMaps(ItemStack stack, ServerLevel level, Entity entity, EquipmentSlot slot, Operation<Void> original) {
        if (Main.CONFIG.features.alwaysUpdateMaps.get()) original.call(stack, level, entity, EquipmentSlot.MAINHAND);
        else original.call(stack, level, entity, slot);
    }
}
