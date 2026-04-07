package me.pajic.affogato_core.mixin.trashslot;

import me.pajic.affogato_core.Main;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractCraftingMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryMenu.class)
public abstract class InventoryMenuMixin extends AbstractCraftingMenu {

    public InventoryMenuMixin(MenuType<?> menuType, int containerId, int width, int height) {
        super(menuType, containerId, width, height);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void addTrashSlot(Inventory inventory, boolean active, Player owner, CallbackInfo ci) {
        if (Main.CONFIG.misc.trashSlot.get()) addSlot(new Slot(inventory, Main.CONFIG.misc.trashSlotId.get() - 5, 77, 44) {
            @Override
            public Identifier getNoItemIcon() {
                return Main.id("container/slot/trash");
            }
        });
    }
}
