package me.pajic.affogato_core.mixson;

import me.pajic.affogato_core.mixson.events.LanguageEvents;

public class MixsonClientInitializer {

    public static void init() {
        LanguageEvents.register();
    }
}
