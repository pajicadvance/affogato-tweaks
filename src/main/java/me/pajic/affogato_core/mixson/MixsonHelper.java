package me.pajic.affogato_core.mixson;

import com.google.gson.JsonElement;
import me.pajic.affogato_core.Main;
import net.ramixin.mixson.Mixson;
import net.ramixin.mixson.MixsonCodecs;
import net.ramixin.mixson.enums.ErrorPolicy;
import net.ramixin.mixson.enums.Lifetime;
import net.ramixin.mixson.util.Index;
import net.ramixin.mixson.util.functions.Event;

import java.util.Set;
import java.util.function.Predicate;

public class MixsonHelper {

    private static final ErrorPolicy ERROR_POLICY = Main.DEBUG ? ErrorPolicy.THROW : ErrorPolicy.LOG;

    public static void registerSingleJson(String eventName, String target, Event<JsonElement> event) {
        Mixson.registerEvent(
                MixsonCodecs.JSON_ELEMENT,
                Mixson.DEFAULT_PRIORITY,
                Lifetime.PERSISTENT,
                ERROR_POLICY,
                eventName,
                index -> index.idEquals(new Index(target)),
                event
        );
    }

    public static void registerMultiJson(String eventName, Set<String> targets, Event<JsonElement> event) {
        Mixson.registerEvent(
                MixsonCodecs.JSON_ELEMENT,
                Mixson.DEFAULT_PRIORITY,
                Lifetime.PERSISTENT,
                ERROR_POLICY,
                eventName,
                index -> targets.stream().anyMatch(s -> index.idEquals(new Index(s))),
                event
        );
    }

    public static void registerMultiJson(String eventName, Predicate<Index> resourcePredicate, Event<JsonElement> event) {
        Mixson.registerEvent(
                MixsonCodecs.JSON_ELEMENT,
                Mixson.DEFAULT_PRIORITY,
                Lifetime.PERSISTENT,
                ERROR_POLICY,
                eventName,
                resourcePredicate,
                event
        );
    }
}
