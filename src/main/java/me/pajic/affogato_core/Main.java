package me.pajic.affogato_core;

import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.pajic.affogato_core.config.ModConfig;
import me.pajic.affogato_core.datapack.ModDatapacks;
import me.pajic.affogato_core.mixson.MixsonInitializer;
import me.pajic.affogato_core.util.ToolMaterialId;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final String MOD_ID = "affogato_core";
    public static final Identifier CONFIG_RL = id("config");
    private static final Logger LOGGER = LoggerFactory.getLogger("Affogato Core");
    public static final boolean DEBUG = FabricLoader.getInstance().isDevelopmentEnvironment();
    public static ModConfig CONFIG = ConfigApiJava.registerAndLoadConfig(ModConfig::new);

    public static final TagKey<Structure> OUTPOSTS = TagKey.create(
            Registries.STRUCTURE,
            id("outposts")
    );
    public static final TagKey<DamageType> NO_EAT_CANCEL = TagKey.create(
            Registries.DAMAGE_TYPE,
            id("no_eat_cancel")
    );

    @Override
    public void onInitialize() {
        MixsonInitializer.init();
        ModDatapacks.init();
        if (CompatFlags.ITEMSWAPPER_LOADED) FabricLoader.getInstance().getModContainer(Main.MOD_ID).ifPresent(modContainer ->
                ResourceManagerHelper.registerBuiltinResourcePack(
                        Main.id("itemswap"),
                        modContainer,
                        Component.literal("ItemSwapper addon for Affogato"),
                        ResourcePackActivationType.ALWAYS_ENABLED
                )
        );
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            if (Main.CONFIG.debug.logMaterialIds.get()) {
                LOGGER.info("Material IDs:");
                ToolMaterialId.MATERIAL_IDS.forEach(LOGGER::info);
            }
        });
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void debugLog(String message, Object ... args) {
        if (DEBUG || CONFIG.debug.logDebugMessages.get()) LOGGER.info(message, args);
    }
}
