package me.pajic.affogato_core.datapack;

import me.pajic.affogato_core.CompatFlags;
import me.pajic.affogato_core.Main;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;

public class ModDatapacks {

    public static void init() {
        FabricLoader.getInstance().getModContainer(Main.MOD_ID).ifPresent(modContainer -> {
            if (Main.CONFIG.features.affogatoItemSwapperAddon.get()) ResourceLoader.registerBuiltinPack(
                    Main.id("itemswap"),
                    modContainer,
                    Component.literal("ItemSwapper addon for Affogato"),
                    PackActivationType.ALWAYS_ENABLED
            );
            if (Main.CONFIG.features.affogatoBlockLootTableEdits.get()) ResourceLoader.registerBuiltinPack(
                    Main.id("block_loot_table_edits"),
                    modContainer,
                    Component.literal("Affogato block loot table edits"),
                    PackActivationType.ALWAYS_ENABLED
            );
            if (Main.CONFIG.features.affogatoEntityLootTableEdits.get()) ResourceLoader.registerBuiltinPack(
                    Main.id("entity_loot_table_edits"),
                    modContainer,
                    Component.literal("Affogato entity loot table edits"),
                    PackActivationType.ALWAYS_ENABLED
            );
            if (Main.CONFIG.features.affogatoRecipeEdits.get()) {
                ResourceLoader.registerBuiltinPack(
                        Main.id("recipe_edits"),
                        modContainer,
                        Component.literal("Affogato recipe edits"),
                        PackActivationType.ALWAYS_ENABLED
                );
                if (CompatFlags.FD_LOADED) ResourceLoader.registerBuiltinPack(
                        Main.id("fd_integration"),
                        modContainer,
                        Component.literal("Farmer's Delight Affogato integration"),
                        PackActivationType.ALWAYS_ENABLED
                );
            }
            if (Main.CONFIG.features.affogatoEarlyGameChanges.get()) {
                ResourceLoader.registerBuiltinPack(
                        Main.id("early_game_changes"),
                        modContainer,
                        Component.literal("Affogato early game changes"),
                        PackActivationType.ALWAYS_ENABLED
                );
            }
            if (CompatFlags.MASTERCUTTER_LOADED && CompatFlags.WILDER_WILD_LOADED) ResourceLoader.registerBuiltinPack(
                    Main.id("wwcutter"),
                    modContainer,
                    Component.literal("Mastercutter recipes for Wilder Wild"),
                    PackActivationType.ALWAYS_ENABLED
            );
        });
    }
}
