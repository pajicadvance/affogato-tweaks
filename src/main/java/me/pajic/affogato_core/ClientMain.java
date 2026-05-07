package me.pajic.affogato_core;

import me.pajic.affogato_core.iris.ShaderPresetSetup;
import me.pajic.affogato_core.mixson.MixsonClientInitializer;
import me.pajic.affogato_core.util.ToolMaterialId;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientMain implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("Affogato Core Client");

    @Override
    public void onInitializeClient() {
        MixsonClientInitializer.init();
        ClientLifecycleEvents.CLIENT_STARTED.register(_ -> {
            if (CompatFlags.IRIS_LOADED) ShaderPresetSetup.init();
            if (Main.CONFIG.debug.logMaterialIds.get()) {
                LOGGER.info("Material IDs:");
                ToolMaterialId.MATERIAL_IDS.forEach(LOGGER::info);
            }
        });
    }
}
