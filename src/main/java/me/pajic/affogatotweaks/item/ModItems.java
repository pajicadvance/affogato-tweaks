package me.pajic.affogatotweaks.item;

import me.pajic.affogatotweaks.Main;
import me.pajic.affogatotweaks.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

public class ModItems {
    public static final Item COPPER_NUGGET = registerModItem("copper_nugget",
            new Item(new Item.Properties())
    );
    public static final Item COPPER_TORCH = registerModItem("copper_torch",
            new StandingAndWallBlockItem(ModBlocks.COPPER_TORCH, ModBlocks.COPPER_WALL_TORCH, new Item.Properties(), Direction.DOWN)
    );
    public static final Item COPPER_BARS = registerModItem("copper_bars",
            new BlockItem(ModBlocks.COPPER_BARS, new Item.Properties())
    );
    public static final Item EXPOSED_COPPER_BARS = registerModItem("exposed_copper_bars",
            new BlockItem(ModBlocks.EXPOSED_COPPER_BARS, new Item.Properties())
    );
    public static final Item WEATHERED_COPPER_BARS = registerModItem("weathered_copper_bars",
            new BlockItem(ModBlocks.WEATHERED_COPPER_BARS, new Item.Properties())
    );
    public static final Item OXIDIZED_COPPER_BARS = registerModItem("oxidized_copper_bars",
            new BlockItem(ModBlocks.OXIDIZED_COPPER_BARS, new Item.Properties())
    );
    public static final Item WAXED_COPPER_BARS = registerModItem("waxed_copper_bars",
            new BlockItem(ModBlocks.WAXED_COPPER_BARS, new Item.Properties())
    );
    public static final Item WAXED_EXPOSED_COPPER_BARS = registerModItem("waxed_exposed_copper_bars",
            new BlockItem(ModBlocks.WAXED_EXPOSED_COPPER_BARS, new Item.Properties())
    );
    public static final Item WAXED_WEATHERED_COPPER_BARS = registerModItem("waxed_weathered_copper_bars",
            new BlockItem(ModBlocks.WAXED_WEATHERED_COPPER_BARS, new Item.Properties())
    );
    public static final Item WAXED_OXIDIZED_COPPER_BARS = registerModItem("waxed_oxidized_copper_bars",
            new BlockItem(ModBlocks.WAXED_OXIDIZED_COPPER_BARS, new Item.Properties())
    );

    private static Item registerModItem(String name, Item item) {
        return Registry.register(
                BuiltInRegistries.ITEM,
                ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, name),
                item
        );
    }

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries ->
                entries.addAfter(Items.TORCH, COPPER_TORCH)
        );
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries ->
                entries.addBefore(Items.IRON_NUGGET, COPPER_NUGGET)
        );
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries ->
                entries.addBefore(Items.COPPER_DOOR,
                        COPPER_BARS, EXPOSED_COPPER_BARS, WEATHERED_COPPER_BARS, OXIDIZED_COPPER_BARS,
                        WAXED_COPPER_BARS, WAXED_EXPOSED_COPPER_BARS, WAXED_WEATHERED_COPPER_BARS, WAXED_OXIDIZED_COPPER_BARS
                )
        );
    }
}
