package me.pajic.affogato_core.mixson;

import me.pajic.affogato_core.mixson.events.LanguageEvents;
import me.pajic.affogato_core.mixson.events.AssetEvents;

public class MixsonClientInitializer {

    public static void init() {
        LanguageEvents.register();
        AssetEvents.register();
    }
}
