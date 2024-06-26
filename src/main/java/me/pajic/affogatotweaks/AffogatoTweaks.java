package me.pajic.affogatotweaks;

import io.wispforest.lavender.book.LavenderBookItem;
import me.pajic.affogatotweaks.loot.LootTableModifier;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

import static me.pajic.affogatotweaks.block.ModBlocks.*;
import static me.pajic.affogatotweaks.item.ModItems.*;

public class AffogatoTweaks implements ModInitializer {

    @Override
    public void onInitialize() {

        // Load built-in datapack
        FabricLoader.getInstance().getModContainer("affogatotweaks").ifPresent(modContainer ->
                ResourceManagerHelper.registerBuiltinResourcePack(new ResourceLocation("affogatotweaks","affogatotweaks"),
                        modContainer, ResourcePackActivationType.ALWAYS_ENABLED));

        // Apply loot table modifications
        LootTableModifier.modifyLootTables();

        // Register blocks
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation("affogatotweaks", "copper_lantern"), COPPER_LANTERN);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation("affogatotweaks", "exposed_copper_lantern"), EXPOSED_COPPER_LANTERN);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation("affogatotweaks", "weathered_copper_lantern"), WEATHERED_COPPER_LANTERN);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation("affogatotweaks", "oxidized_copper_lantern"), OXIDIZED_COPPER_LANTERN);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation("affogatotweaks", "waxed_copper_lantern"), WAXED_COPPER_LANTERN);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation("affogatotweaks", "waxed_exposed_copper_lantern"), WAXED_EXPOSED_COPPER_LANTERN);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation("affogatotweaks", "waxed_weathered_copper_lantern"), WAXED_WEATHERED_COPPER_LANTERN);
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation("affogatotweaks", "waxed_oxidized_copper_lantern"), WAXED_OXIDIZED_COPPER_LANTERN);

        // Add oxidizable registry entries
        OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER_LANTERN, EXPOSED_COPPER_LANTERN);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER_LANTERN, WEATHERED_COPPER_LANTERN);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_COPPER_LANTERN, OXIDIZED_COPPER_LANTERN);
        OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER_LANTERN, WAXED_COPPER_LANTERN);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER_LANTERN, WAXED_EXPOSED_COPPER_LANTERN);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER_LANTERN, WAXED_WEATHERED_COPPER_LANTERN);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER_LANTERN, WAXED_OXIDIZED_COPPER_LANTERN);

        // Register items
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("affogatotweaks", "copper_lantern"), new BlockItem(COPPER_LANTERN, new FabricItemSettings()));
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("affogatotweaks", "exposed_copper_lantern"), new BlockItem(EXPOSED_COPPER_LANTERN, new FabricItemSettings()));
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("affogatotweaks", "weathered_copper_lantern"), new BlockItem(WEATHERED_COPPER_LANTERN, new FabricItemSettings()));
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("affogatotweaks", "oxidized_copper_lantern"), new BlockItem(OXIDIZED_COPPER_LANTERN, new FabricItemSettings()));
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("affogatotweaks", "waxed_copper_lantern"), new BlockItem(WAXED_COPPER_LANTERN, new FabricItemSettings()));
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("affogatotweaks", "waxed_exposed_copper_lantern"), new BlockItem(WAXED_EXPOSED_COPPER_LANTERN, new FabricItemSettings()));
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("affogatotweaks", "waxed_weathered_copper_lantern"), new BlockItem(WAXED_WEATHERED_COPPER_LANTERN, new FabricItemSettings()));
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("affogatotweaks", "waxed_oxidized_copper_lantern"), new BlockItem(WAXED_OXIDIZED_COPPER_LANTERN, new FabricItemSettings()));
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("affogatotweaks", "copper_nugget"), COPPER_NUGGET);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("affogatotweaks", "enchantment_upgrade"), ENCHANTMENT_UPGRADE_SMITHING_TEMPLATE);

        // Add items to item groups
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(content -> content.addAfter(Items.IRON_NUGGET, COPPER_NUGGET));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(content -> content.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ENCHANTMENT_UPGRADE_SMITHING_TEMPLATE));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(content -> {
            content.addAfter(Items.LANTERN, COPPER_LANTERN);
            content.addAfter(COPPER_LANTERN, EXPOSED_COPPER_LANTERN);
            content.addAfter(EXPOSED_COPPER_LANTERN, WEATHERED_COPPER_LANTERN);
            content.addAfter(WEATHERED_COPPER_LANTERN, OXIDIZED_COPPER_LANTERN);
            content.addAfter(OXIDIZED_COPPER_LANTERN, WAXED_COPPER_LANTERN);
            content.addAfter(WAXED_COPPER_LANTERN, WAXED_EXPOSED_COPPER_LANTERN);
            content.addAfter(WAXED_EXPOSED_COPPER_LANTERN, WAXED_WEATHERED_COPPER_LANTERN);
            content.addAfter(WAXED_WEATHERED_COPPER_LANTERN, WAXED_OXIDIZED_COPPER_LANTERN);
        });

        // Register Lavender guide book item
        LavenderBookItem.registerForBook(new ResourceLocation("affogatotweaks", "affogato_guide"), new FabricItemSettings().stacksTo(1));
    }
}
