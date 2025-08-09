package me.pajic.affogatotweaks.item;

import me.pajic.affogatotweaks.Main;
import me.pajic.affogatotweaks.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.StandingAndWallBlockItem;

public class ModItems {
    public static final Item COPPER_TORCH_ITEM = Registry.register(
            BuiltInRegistries.ITEM,
            ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "copper_torch"),
            new StandingAndWallBlockItem(ModBlocks.COPPER_TORCH, ModBlocks.COPPER_WALL_TORCH, new Item.Properties(), Direction.DOWN)
    );
    public static final Item COPPER_NUGGET = Registry.register(
            BuiltInRegistries.ITEM,
            ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "copper_nugget"),
            new Item(new Item.Properties())
    );

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries ->
                entries.addAfter(Items.TORCH, ModItems.COPPER_TORCH_ITEM)
        );
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries ->
                entries.addBefore(Items.IRON_NUGGET, ModItems.COPPER_NUGGET)
        );
    }
}
