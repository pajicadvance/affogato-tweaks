package me.pajic.affogato_core.mixson;

import me.pajic.affogato_core.Main;
import net.ramixin.mixson.debug.DebugMode;
import net.ramixin.mixson.inline.Mixson;

public class MixsonInitializer {

    public static void init() {
        if (Main.DEBUG || Main.CONFIG.debug.exportJsonPatches.get()) Mixson.setDebugMode(DebugMode.EXPORT);
        AdvancementDataEvents.register();
        LootTableEvents.register();
        RecipeEvents.register();
        TagEvents.register();
        WorldgenDataEvents.register();
    }
}
