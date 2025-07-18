package me.pajic.affogatotweaks.mixson;

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
    }
}
