package me.pajic.affogato_core.datapack;

import me.pajic.affogato_core.CompatFlags;
import me.pajic.affogato_core.Main;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.network.chat.Component;

public class ModDatapacks {

    private static final ModContainer CONTAINER = FabricLoader.getInstance().getModContainer(Main.MOD_ID).orElseThrow();

    public static void init() {
        if (Main.CONFIG.features.affogatoBlockLootTableEdits.get()) {
            registerPersistentPack("block_loot_table_edits/vanilla", "Affogato vanilla block loot table edits");
            if (CompatFlags.WILDER_WILD_LOADED) registerPersistentPack("block_loot_table_edits/wilderwild", "Affogato Wilder Wild block loot table edits");
            if (CompatFlags.TRAVERSE_LOADED) registerPersistentPack("block_loot_table_edits/traverse", "Affogato Traverse block loot table edits");
        }
        if (Main.CONFIG.features.affogatoEntityLootTableEdits.get())
            registerPersistentPack("entity_loot_table_edits", "Affogato entity loot table edits");
        if (Main.CONFIG.features.affogatoRecipeEdits.get())
            registerPersistentPack("recipe_edits", "Affogato recipe edits");
        if (Main.CONFIG.features.affogatoEarlyGameChanges.get())
            registerPersistentPack("early_game_changes", "Affogato early game changes");
        if (CompatFlags.FD_LOADED && Main.CONFIG.features.affogatoFDAddon.get())
            registerPersistentPack("fd_integration", "Farmer's Delight Affogato integration");
        if (CompatFlags.ITEMSWAPPER_LOADED && Main.CONFIG.features.affogatoItemSwapperAddon.get())
            registerPersistentPack("itemswap", "ItemSwapper addon for Affogato");
        if (CompatFlags.MASTERCUTTER_LOADED) {
            if (CompatFlags.WILDER_WILD_LOADED)
                registerPersistentPack("wwcutter", "Mastercutter recipes for Wilder Wild");
            if (CompatFlags.TRAVERSE_LOADED)
                registerPersistentPack("traversecutter", "Mastercutter recipes for Traverse");
        }
    }

    private static void registerPersistentPack(String id, String name) {
        ResourceLoader.registerBuiltinPack(Main.id(id), CONTAINER, Component.literal(name), PackActivationType.ALWAYS_ENABLED);
    }
}
