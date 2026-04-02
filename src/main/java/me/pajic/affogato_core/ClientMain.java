package me.pajic.affogato_core;

import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import me.pajic.affogato_core.config.ModClientConfig;
import me.pajic.affogato_core.mixson.MixsonClientInitializer;
import me.pajic.affogato_core.util.ToolMaterialId;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientMain implements ClientModInitializer {

    public static final Identifier CONFIG_RL = Main.id("client_config");
    private static final Logger LOGGER = LoggerFactory.getLogger("Affogato Core Client");
    public static ModClientConfig CONFIG = ConfigApiJava.registerAndLoadConfig(ModClientConfig::new, RegisterType.CLIENT);

    @Override
    public void onInitializeClient() {
        MixsonClientInitializer.init();
        ClientLifecycleEvents.CLIENT_STARTED.register(_ -> {
            if (Main.CONFIG.debug.logMaterialIds.get()) {
                LOGGER.info("Material IDs:");
                ToolMaterialId.MATERIAL_IDS.forEach(LOGGER::info);
            }
        });
    }
}
