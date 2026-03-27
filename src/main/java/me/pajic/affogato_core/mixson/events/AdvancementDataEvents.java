package me.pajic.affogato_core.mixson.events;

import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.mixson.MixsonHelper;

import java.util.Set;

public class AdvancementDataEvents {
    public static void register() {
        if (Main.CONFIG.features.nightVisionNuke.get()) MixsonHelper.registerMultiJson(
                "Remove Night Vision requirement for advancements",
                Set.of("minecraft:advancement/nether/all_effects", "minecraft:advancement/nether/all_potions"),
                context -> context.getFile().getAsJsonObject()
                        .getAsJsonObject("criteria")
                        .getAsJsonObject("all_effects")
                        .getAsJsonObject("conditions")
                        .getAsJsonObject("effects")
                        .remove("minecraft:night_vision")
        );
        if (Main.CONFIG.features.villagerNuke.get()) {
            MixsonHelper.registerSingleJson(
                    "Remove Hero of the Village requirement for advancements",
                    "minecraft:advancement/nether/all_effects",
                    context -> context.getFile().getAsJsonObject()
                            .getAsJsonObject("criteria")
                            .getAsJsonObject("all_effects")
                            .getAsJsonObject("conditions")
                            .getAsJsonObject("effects")
                            .remove("minecraft:hero_of_the_village")
            );
            MixsonHelper.registerSingleJson(
                    "Change summon iron golem advancement prerequisite",
                    "minecraft:advancement/adventure/summon_iron_golem",
                    context -> context.getFile().getAsJsonObject().addProperty("parent", "minecraft:adventure/root")
            );
            MixsonHelper.registerMultiJson(
                    "Disable village related advancements",
                    Set.of(
                            "minecraft:advancement/adventure/hero_of_the_village",
                            "minecraft:advancement/adventure/trade",
                            "minecraft:advancement/adventure/trade_at_world_height",
                            "minecraft:advancement/adventure/very_very_frightening",
                            "minecraft:advancement/adventure/lightning_rod_with_villager_no_fire"
                    ),
                    context -> context.markForDeletion(true)
            );
            MixsonHelper.registerSingleJson(
                    "Remove village references in advancement text",
                    "minecraft:lang/en_us",
                    context -> {
                        context.getFile().getAsJsonObject().addProperty("advancements.adventure.voluntary_exile.description", "Kill a raid captain. Maybe consider staying away from outposts for the time being...");
                        context.getFile().getAsJsonObject().addProperty("advancements.adventure.summon_iron_golem.description", "Summon an Iron Golem");
                    }
            );
        }
    }
}
