package me.pajic.affogato_core.datapack;

import me.pajic.affogato_core.CompatFlags;
import me.pajic.affogato_core.Main;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;

public class ModDatapacks {

    public static void init() {
        FabricLoader.getInstance().getModContainer(Main.MOD_ID).ifPresent(modContainer -> {
            if (Main.CONFIG.features.affogatoItemSwapperAddon.get()) ResourceManagerHelper.registerBuiltinResourcePack(
                    Main.id("itemswap"),
                    modContainer,
                    Component.literal("ItemSwapper addon for Affogato"),
                    ResourcePackActivationType.ALWAYS_ENABLED
            );
            if (Main.CONFIG.features.affogatoBlockLootTableEdits.get()) ResourceManagerHelper.registerBuiltinResourcePack(
                    Main.id("block_loot_table_edits"),
                    modContainer,
                    Component.literal("Affogato block loot table edits"),
                    ResourcePackActivationType.ALWAYS_ENABLED
            );
            if (Main.CONFIG.features.affogatoEntityLootTableEdits.get()) ResourceManagerHelper.registerBuiltinResourcePack(
                    Main.id("entity_loot_table_edits"),
                    modContainer,
                    Component.literal("Affogato entity loot table edits"),
                    ResourcePackActivationType.ALWAYS_ENABLED
            );
            if (Main.CONFIG.features.affogatoRecipeEdits.get()) {
                ResourceManagerHelper.registerBuiltinResourcePack(
                        Main.id("recipe_edits"),
                        modContainer,
                        Component.literal("Affogato recipe edits"),
                        ResourcePackActivationType.ALWAYS_ENABLED
                );
                if (CompatFlags.FD_LOADED) ResourceManagerHelper.registerBuiltinResourcePack(
                        Main.id("fd_cake_recipe"),
                        modContainer,
                        Component.literal("Farmer's Delight vanilla cake recipe"),
                        ResourcePackActivationType.ALWAYS_ENABLED
                );
            }
            if (CompatFlags.MASTERCUTTER_LOADED && CompatFlags.WILDER_WILD_LOADED) ResourceManagerHelper.registerBuiltinResourcePack(
                    Main.id("wwcutter"),
                    modContainer,
                    Component.literal("Mastercutter recipes for Wilder Wild"),
                    ResourcePackActivationType.ALWAYS_ENABLED
            );
        });
    }
}
