package me.pajic.affogato_core.mixin.trashslot;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogato_core.Main;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractContainerMenu.class)
public abstract class AbstractContainerMenuMixin {

    @Definition(id = "setCarried", method = "Lnet/minecraft/world/inventory/AbstractContainerMenu;setCarried(Lnet/minecraft/world/item/ItemStack;)V")
    @Definition(id = "clicked", local = @Local(type = ItemStack.class, name = "clicked"))
    @Expression("this.setCarried(@(clicked))")
    @ModifyExpressionValue(
            method = "doClick",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private ItemStack trashItem(ItemStack original, @Local(name = "slotIndex", argsOnly = true) int slotIndex) {
        return slotIndex == Main.CONFIG.misc.trashSlotId.get() ? ItemStack.EMPTY : original;
    }
}
