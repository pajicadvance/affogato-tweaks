package me.pajic.affogato_core.config;

import net.fabricmc.loader.api.FabricLoader;

public class ModClientConfigHolder {

    private static ModClientConfig CONFIG;

    public static ModClientConfig options() {
        if (CONFIG == null) init();
        return CONFIG;
    }

    public static void init() {
        CONFIG = ModClientConfig.load(FabricLoader.getInstance().getConfigDir().resolve("affogato_core/config-client.json").toFile());
    }
}
