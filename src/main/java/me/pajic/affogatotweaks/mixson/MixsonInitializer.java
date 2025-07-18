package me.pajic.affogatotweaks.mixson;

import net.fabricmc.loader.api.FabricLoader;
import net.ramixin.mixson.debug.DebugMode;
import net.ramixin.mixson.inline.Mixson;

public class MixsonInitializer {
    public static void init() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) Mixson.setDebugMode(DebugMode.EXPORT);
        AdvancementDataEvents.register();
        LootTableEvents.register();
        RecipeEvents.register();
        TagEvents.register();
        WorldgenDataEvents.register();
    }
}
