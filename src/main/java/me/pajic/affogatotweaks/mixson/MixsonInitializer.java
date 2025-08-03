package me.pajic.affogatotweaks.mixson;

import me.pajic.affogatotweaks.Main;
import net.ramixin.mixson.debug.DebugMode;
import net.ramixin.mixson.inline.Mixson;

public class MixsonInitializer {
    public static void init() {
        if (Main.DEBUG) Mixson.setDebugMode(DebugMode.EXPORT);
        AdvancementDataEvents.register();
        LootTableEvents.register();
        RecipeEvents.register();
        TagEvents.register();
        WorldgenDataEvents.register();
    }
}
