package me.pajic.affogato_core.mixin.gameplay;

import me.pajic.affogato_core.Main;
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
import java.util.List;
import java.util.Set;

@Mixin(CreativeModeTab.class)
public class CreativeModeTabMixin {

    @Unique private static final List<String> TOOLS = List.of(
            "minecraft:stone_pickaxe",
            "minecraft:stone_shovel",
            "minecraft:stone_sword",
            "minecraft:stone_axe",
            "minecraft:stone_hoe",
            "minecraft:wooden_pickaxe",
            "minecraft:wooden_shovel",
            "minecraft:wooden_sword",
            "minecraft:wooden_axe",
            "minecraft:wooden_hoe"
    );

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
                Main.CONFIG.hiddenItems.get().contains(BuiltInRegistries.ITEM.getKey(stack.getItem()).toString())
        );
        if (Main.CONFIG.features.affogatoEarlyGameChanges.get()) items.removeIf(stack ->
                TOOLS.contains(BuiltInRegistries.ITEM.getKey(stack.getItem()).toString())
        );
    }
}
