package me.pajic.affogatotweaks.mixin.integration.inmis;

import com.llamalad7.mixinextras.sugar.Local;
import draylar.inmis.Inmis;
import draylar.inmis.item.component.BackpackComponent;
import me.pajic.affogatotweaks.values.MiscValues;
import net.minecraft.core.NonNullList;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SmithingMenu.class)
public class SmithingMenuMixin {

    @Inject(
            method = "createResult",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;isItemEnabled(Lnet/minecraft/world/flag/FeatureFlagSet;)Z"
            )
    )
    private void handleBlazingBackpackUpgrade(CallbackInfo ci, @Local ItemStack itemStack) {
        BackpackComponent component = itemStack.get(Inmis.BACKPACK_COMPONENT);
        if (component != null) {
            SimpleContainer container = component.getSimpleInventory();
            NonNullList<ItemStack> items = NonNullList.withSize(container.getContainerSize() + MiscValues.BACKPACK_SLOTS_PER_UPGRADE, ItemStack.EMPTY);
            for (int i = 0; i < container.getContainerSize(); i++) items.set(i, container.getItem(i));
            itemStack.set(Inmis.BACKPACK_COMPONENT, new BackpackComponent(items));
        }
    }
}
