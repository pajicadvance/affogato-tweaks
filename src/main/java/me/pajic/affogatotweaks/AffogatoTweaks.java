package me.pajic.affogatotweaks;

import io.wispforest.lavender.book.LavenderBookItem;
import me.pajic.affogatotweaks.block.CopperLanternBlock;
import me.pajic.affogatotweaks.loot.LootTableModifier;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.MapColor;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class AffogatoTweaks implements ModInitializer {

    // Copper lantern variants
    public static final CopperLanternBlock COPPER_LANTERN = new CopperLanternBlock(
            Oxidizable.OxidationLevel.UNAFFECTED,
            FabricBlockSettings.create().mapColor(MapColor.ORANGE).solid().requiresTool().strength(3.0F, 6.0F).sounds(BlockSoundGroup.LANTERN).luminance(state -> 14).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final CopperLanternBlock EXPOSED_COPPER_LANTERN = new CopperLanternBlock(
            Oxidizable.OxidationLevel.EXPOSED,
            FabricBlockSettings.create().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).solid().requiresTool().strength(3.0F, 6.0F).sounds(BlockSoundGroup.LANTERN).luminance(state -> 11).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
    );

    public static final CopperLanternBlock WEATHERED_COPPER_LANTERN = new CopperLanternBlock(
            Oxidizable.OxidationLevel.WEATHERED,
            FabricBlockSettings.create().mapColor(MapColor.DARK_AQUA).solid().requiresTool().strength(3.0F, 6.0F).sounds(BlockSoundGroup.LANTERN).luminance(state -> 7).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final CopperLanternBlock OXIDIZED_COPPER_LANTERN = new CopperLanternBlock(
            Oxidizable.OxidationLevel.OXIDIZED,
            FabricBlockSettings.create().mapColor(MapColor.TEAL).solid().requiresTool().strength(3.0F, 6.0F).sounds(BlockSoundGroup.LANTERN).luminance(state -> 3).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
    );

    // Copper nugget
    public static final Item COPPER_NUGGET = new Item(new FabricItemSettings());

    @Override
    public void onInitialize() {

        // Load built-in datapack
        FabricLoader.getInstance().getModContainer("affogatotweaks").ifPresent(modContainer ->
                ResourceManagerHelper.registerBuiltinResourcePack(new Identifier("affogatotweaks","affogatotweaks"),
                        modContainer, ResourcePackActivationType.ALWAYS_ENABLED));

        // Apply loot table modifications
        LootTableModifier.modifyLootTables();

        // Register copper lantern blocks
        Registry.register(Registries.BLOCK, new Identifier("affogatotweaks", "copper_lantern"), COPPER_LANTERN);
        Registry.register(Registries.BLOCK, new Identifier("affogatotweaks", "exposed_copper_lantern"), EXPOSED_COPPER_LANTERN);
        Registry.register(Registries.BLOCK, new Identifier("affogatotweaks", "weathered_copper_lantern"), WEATHERED_COPPER_LANTERN);
        Registry.register(Registries.BLOCK, new Identifier("affogatotweaks", "oxidized_copper_lantern"), OXIDIZED_COPPER_LANTERN);

        // Add oxidizable registry entries
        OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER_LANTERN, EXPOSED_COPPER_LANTERN);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER_LANTERN, WEATHERED_COPPER_LANTERN);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_COPPER_LANTERN, OXIDIZED_COPPER_LANTERN);

        // Register copper lantern and copper nugget items
        Registry.register(Registries.ITEM, new Identifier("affogatotweaks", "copper_lantern"), new BlockItem(COPPER_LANTERN, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier("affogatotweaks", "exposed_copper_lantern"), new BlockItem(EXPOSED_COPPER_LANTERN, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier("affogatotweaks", "weathered_copper_lantern"), new BlockItem(WEATHERED_COPPER_LANTERN, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier("affogatotweaks", "oxidized_copper_lantern"), new BlockItem(OXIDIZED_COPPER_LANTERN, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier("affogatotweaks", "copper_nugget"), COPPER_NUGGET);

        // Add items to their respective item groups
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> content.addAfter(Items.IRON_NUGGET, COPPER_NUGGET));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
            content.addAfter(Items.LANTERN, COPPER_LANTERN);
            content.addAfter(COPPER_LANTERN, EXPOSED_COPPER_LANTERN);
            content.addAfter(EXPOSED_COPPER_LANTERN, WEATHERED_COPPER_LANTERN);
            content.addAfter(WEATHERED_COPPER_LANTERN, OXIDIZED_COPPER_LANTERN);
        });

        // Register Lavender guide book item
        LavenderBookItem.registerForBook(new Identifier("affogatotweaks", "affogato_guide"), new FabricItemSettings().maxCount(1));
    }
}
