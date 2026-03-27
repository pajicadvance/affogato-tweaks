package me.pajic.affogato_core.mixson;

import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.mixson.events.*;
import net.ramixin.mixson.Mixson;
import net.ramixin.mixson.enums.DebugOption;

public class MixsonInitializer {

    public static void init() {
        if (Main.DEBUG || Main.CONFIG.debug.exportJsonPatches.get()) {
            Mixson.enableDebugOption(DebugOption.BASIC_LOGGING);
            Mixson.enableDebugOption(DebugOption.EXTRA_LOGGING);
            Mixson.enableDebugOption(DebugOption.EXPORT_PATCHED_FILE);
        }

        AdvancementDataEvents.register();
        LootTableEvents.register();
        RecipeEvents.register();
        TagEvents.register();
        WorldgenDataEvents.register();
    }
}
