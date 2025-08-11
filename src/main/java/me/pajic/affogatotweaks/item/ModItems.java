package me.pajic.affogatotweaks.item;

import me.pajic.affogatotweaks.Main;
import me.pajic.affogatotweaks.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;

public class ModItems {
    public static final Item COPPER_NUGGET = registerModItem("copper_nugget",
            new Item(new Item.Properties())
    );
    public static final Item COAL_NUGGET = registerModItem("coal_nugget",
            new Item(new Item.Properties())
    );
    public static final Item CHARCOAL_NUGGET = registerModItem("charcoal_nugget",
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
    public static final Item COPPER_CHAIN = registerModItem("copper_chain",
            new BlockItem(ModBlocks.COPPER_CHAIN, new Item.Properties())
    );
    public static final Item EXPOSED_COPPER_CHAIN = registerModItem("exposed_copper_chain",
            new BlockItem(ModBlocks.EXPOSED_COPPER_CHAIN, new Item.Properties())
    );
    public static final Item WEATHERED_COPPER_CHAIN = registerModItem("weathered_copper_chain",
            new BlockItem(ModBlocks.WEATHERED_COPPER_CHAIN, new Item.Properties())
    );
    public static final Item OXIDIZED_COPPER_CHAIN = registerModItem("oxidized_copper_chain",
            new BlockItem(ModBlocks.OXIDIZED_COPPER_CHAIN, new Item.Properties())
    );
    public static final Item WAXED_COPPER_CHAIN = registerModItem("waxed_copper_chain",
            new BlockItem(ModBlocks.WAXED_COPPER_CHAIN, new Item.Properties())
    );
    public static final Item WAXED_EXPOSED_COPPER_CHAIN = registerModItem("waxed_exposed_copper_chain",
            new BlockItem(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN, new Item.Properties())
    );
    public static final Item WAXED_WEATHERED_COPPER_CHAIN = registerModItem("waxed_weathered_copper_chain",
            new BlockItem(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN, new Item.Properties())
    );
    public static final Item WAXED_OXIDIZED_COPPER_CHAIN = registerModItem("waxed_oxidized_copper_chain",
            new BlockItem(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN, new Item.Properties())
    );
    public static final Item EXPOSED_LIGHTNING_ROD = registerModItem("exposed_lightning_rod",
            new BlockItem(ModBlocks.EXPOSED_LIGHTNING_ROD, new Item.Properties())
    );
    public static final Item WEATHERED_LIGHTNING_ROD = registerModItem("weathered_lightning_rod",
            new BlockItem(ModBlocks.WEATHERED_LIGHTNING_ROD, new Item.Properties())
    );
    public static final Item OXIDIZED_LIGHTNING_ROD = registerModItem("oxidized_lightning_rod",
            new BlockItem(ModBlocks.OXIDIZED_LIGHTNING_ROD, new Item.Properties())
    );
    public static final Item WAXED_LIGHTNING_ROD = registerModItem("waxed_lightning_rod",
            new BlockItem(ModBlocks.WAXED_LIGHTNING_ROD, new Item.Properties())
    );
    public static final Item WAXED_EXPOSED_LIGHTNING_ROD = registerModItem("waxed_exposed_lightning_rod",
            new BlockItem(ModBlocks.WAXED_EXPOSED_LIGHTNING_ROD, new Item.Properties())
    );
    public static final Item WAXED_WEATHERED_LIGHTNING_ROD = registerModItem("waxed_weathered_lightning_rod",
            new BlockItem(ModBlocks.WAXED_WEATHERED_LIGHTNING_ROD, new Item.Properties())
    );
    public static final Item WAXED_OXIDIZED_LIGHTNING_ROD = registerModItem("waxed_oxidized_lightning_rod",
            new BlockItem(ModBlocks.WAXED_OXIDIZED_LIGHTNING_ROD, new Item.Properties())
    );
    public static final Item COPPER_LANTERN = registerModItem("copper_lantern",
            new BlockItem(ModBlocks.COPPER_LANTERN, new Item.Properties())
    );
    public static final Item EXPOSED_COPPER_LANTERN = registerModItem("exposed_copper_lantern",
            new BlockItem(ModBlocks.EXPOSED_COPPER_LANTERN, new Item.Properties())
    );
    public static final Item WEATHERED_COPPER_LANTERN = registerModItem("weathered_copper_lantern",
            new BlockItem(ModBlocks.WEATHERED_COPPER_LANTERN, new Item.Properties())
    );
    public static final Item OXIDIZED_COPPER_LANTERN = registerModItem("oxidized_copper_lantern",
            new BlockItem(ModBlocks.OXIDIZED_COPPER_LANTERN, new Item.Properties())
    );
    public static final Item WAXED_COPPER_LANTERN = registerModItem("waxed_copper_lantern",
            new BlockItem(ModBlocks.WAXED_COPPER_LANTERN, new Item.Properties())
    );
    public static final Item WAXED_EXPOSED_COPPER_LANTERN = registerModItem("waxed_exposed_copper_lantern",
            new BlockItem(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN, new Item.Properties())
    );
    public static final Item WAXED_WEATHERED_COPPER_LANTERN = registerModItem("waxed_weathered_copper_lantern",
            new BlockItem(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN, new Item.Properties())
    );
    public static final Item WAXED_OXIDIZED_COPPER_LANTERN = registerModItem("waxed_oxidized_copper_lantern",
            new BlockItem(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN, new Item.Properties())
    );

    private static Item registerModItem(String name, Item item) {
        return Registry.register(
                BuiltInRegistries.ITEM,
                Main.withModNamespace(name),
                item
        );
    }

    public static void init() {
        FuelRegistry.INSTANCE.add(COAL_NUGGET, 200);
        FuelRegistry.INSTANCE.add(CHARCOAL_NUGGET, 200);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
            entries.addAfter(Items.TORCH, COPPER_TORCH);
            entries.addAfter(Items.SOUL_LANTERN,
                    COPPER_LANTERN, EXPOSED_COPPER_LANTERN, WEATHERED_COPPER_LANTERN, OXIDIZED_COPPER_LANTERN,
                    WAXED_COPPER_LANTERN, WAXED_EXPOSED_COPPER_LANTERN, WAXED_WEATHERED_COPPER_LANTERN, WAXED_OXIDIZED_COPPER_LANTERN
            );
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.addBefore(Items.IRON_NUGGET, COPPER_NUGGET);
            entries.addAfter(Items.COAL, COAL_NUGGET);
            entries.addAfter(Items.CHARCOAL, CHARCOAL_NUGGET);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
            entries.addBefore(Items.COPPER_DOOR,
                    COPPER_BARS, EXPOSED_COPPER_BARS, WEATHERED_COPPER_BARS, OXIDIZED_COPPER_BARS,
                    WAXED_COPPER_BARS, WAXED_EXPOSED_COPPER_BARS, WAXED_WEATHERED_COPPER_BARS, WAXED_OXIDIZED_COPPER_BARS
            );
            entries.addAfter(Items.WAXED_OXIDIZED_COPPER_BULB,
                    COPPER_CHAIN, EXPOSED_COPPER_CHAIN, WEATHERED_COPPER_CHAIN, OXIDIZED_COPPER_CHAIN,
                    WAXED_COPPER_CHAIN, WAXED_EXPOSED_COPPER_CHAIN, WAXED_WEATHERED_COPPER_CHAIN, WAXED_OXIDIZED_COPPER_CHAIN
            );
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.REDSTONE_BLOCKS).register(entries ->
                entries.addAfter(Items.LIGHTNING_ROD,
                        EXPOSED_LIGHTNING_ROD, WEATHERED_LIGHTNING_ROD, OXIDIZED_LIGHTNING_ROD,
                        WAXED_LIGHTNING_ROD, WAXED_EXPOSED_LIGHTNING_ROD, WAXED_WEATHERED_LIGHTNING_ROD, WAXED_OXIDIZED_LIGHTNING_ROD
                )
        );
    }
}
