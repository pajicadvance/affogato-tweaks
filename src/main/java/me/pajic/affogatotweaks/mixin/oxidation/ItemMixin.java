package me.pajic.affogatotweaks.mixin.oxidation;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.pajic.affogatotweaks.oxidation.OxidationUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.class)
public class ItemMixin {

    @WrapMethod(method = "inventoryTick")
    private void oxidizePlayerCopperGear(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected, Operation<Void> original) {
        OxidationUtil.tryOxidize(stack, level.getDayTime(), level.getRandom(), false);
        original.call(stack, level, entity, slotId, isSelected);
    }
}
