package me.pajic.affogato_core;

import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.pajic.affogato_core.config.ModConfig;
import me.pajic.affogato_core.datapack.ModDatapacks;
import me.pajic.affogato_core.item.ModItems;
import me.pajic.affogato_core.mixson.MixsonInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final String MOD_ID = "affogato_core";
    public static final Identifier CONFIG_RL = id("config");
    private static final Logger LOGGER = LoggerFactory.getLogger("Affogato Core");
    public static final boolean DEBUG = FabricLoader.getInstance().isDevelopmentEnvironment();
    public static ModConfig CONFIG = ConfigApiJava.registerAndLoadConfig(ModConfig::new);

    @Override
    public void onInitialize() {
        MixsonInitializer.init();
        ModItems.init();
        ModDatapacks.init();
        if (CompatFlags.ITEMSWAPPER_LOADED) FabricLoader.getInstance().getModContainer(Main.MOD_ID).ifPresent(modContainer ->
                ResourceLoader.registerBuiltinPack(
                        Main.id("itemswap"),
                        modContainer,
                        Component.literal("ItemSwapper addon for Affogato"),
                        PackActivationType.ALWAYS_ENABLED
                )
        );
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void debugLog(String message, Object ... args) {
        if (DEBUG || CONFIG.debug.logDebugMessages.get()) LOGGER.info(message, args);
    }
}
