package me.pajic.affogatotweaks.mixson;

import net.fabricmc.loader.api.FabricLoader;
import net.ramixin.mixson.inline.Mixson;

public class AdvancementDataEvents {
    public static void register() {
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:advancement/nether/all_effects") || rl.toString().equals("minecraft:advancement/nether/all_potions"),
                "Remove Night Vision requirement for advancements",
                context -> context.getFile().getAsJsonObject()
                        .getAsJsonObject("criteria")
                        .getAsJsonObject("all_effects")
                        .getAsJsonObject("conditions")
                        .getAsJsonObject("effects")
                        .remove("minecraft:night_vision"),
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:advancement/nether/all_effects"),
                "Remove Hero of the Village requirement for advancements",
                context -> context.getFile().getAsJsonObject()
                        .getAsJsonObject("criteria")
                        .getAsJsonObject("all_effects")
                        .getAsJsonObject("conditions")
                        .getAsJsonObject("effects")
                        .remove("minecraft:hero_of_the_village"),
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:advancement/adventure/summon_iron_golem"),
                "Change summon iron golem advancement prerequisite",
                context -> context.getFile().getAsJsonObject().addProperty("parent", "minecraft:adventure/root"),
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl ->
                        rl.toString().equals("minecraft:advancement/story/cure_zombie_villager") ||
                        rl.toString().equals("minecraft:advancement/adventure/hero_of_the_village") ||
                        rl.toString().equals("minecraft:advancement/adventure/trade") ||
                        rl.toString().equals("minecraft:advancement/adventure/trade_at_world_height") ||
                        rl.toString().equals("minecraft:advancement/adventure/very_very_frightening") ||
                        rl.toString().equals("minecraft:advancement/adventure/lightning_rod_with_villager_no_fire"),
                "Disable village related advancements",
                context -> context.markForDeletion(true),
                true
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:lang/en_us"),
                "Remove village references in advancement text",
                context -> {
                    context.getFile().getAsJsonObject().addProperty("advancements.adventure.voluntary_exile.description", "Kill a raid captain.");
                    context.getFile().getAsJsonObject().addProperty("advancements.adventure.summon_iron_golem.description", "Summon an Iron Golem");
                },
                true
        );
        if (FabricLoader.getInstance().isModLoaded("inmis")) Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().startsWith("inmis:advancement/"),
                "Remove backpack advancements",
                context -> context.markForDeletion(true),
                true
        );
    }
}
