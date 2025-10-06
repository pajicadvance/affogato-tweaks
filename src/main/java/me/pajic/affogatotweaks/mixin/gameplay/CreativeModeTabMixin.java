package me.pajic.affogatotweaks.mixin.gameplay;

import me.pajic.affogatotweaks.values.MiscValues;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.Set;

@Mixin(CreativeModeTab.class)
public class CreativeModeTabMixin {

    @Shadow private Collection<ItemStack> displayItems;
    @Shadow private Set<ItemStack> displayItemsSearchTab;

    @Inject(
            method = "buildContents",
            at = @At("TAIL")
    )
    private void hideItems(CallbackInfo ci) {
        filter(displayItems);
        filter(displayItemsSearchTab);
    }

    @Unique
    private void filter(Collection<ItemStack> items) {
        items.removeIf(stack ->
            MiscValues.HIDDEN_ITEMS.contains(BuiltInRegistries.ITEM.getKey(stack.getItem()).toString())
        );
    }
}
