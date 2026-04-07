package me.pajic.affogato_core.mixin.trashslot;

import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogato_core.Main;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends AbstractRecipeBookScreen<InventoryMenu> {

    public InventoryScreenMixin(InventoryMenu menu, RecipeBookComponent<?> recipeBookComponent, Inventory inventory, Component title) {
        super(menu, recipeBookComponent, inventory, title);
    }

    @Inject(
            method = "extractBackground",
            at = @At("TAIL")
    )
    private void renderTrashSlot(
            GuiGraphicsExtractor graphics,
            int mouseX, int mouseY, float a,
            CallbackInfo ci,
            @Local(name = "xo") int xo,
            @Local(name = "yo") int yo
    ) {
        if (Main.CONFIG.misc.trashSlot.get()) graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                Identifier.withDefaultNamespace("container/slot"),
                xo + 76, yo + 43,
                18, 18
        );
    }
}
