package me.pajic.affogatotweaks.mixson;

import net.ramixin.mixson.inline.Mixson;

public class LanguageEvents {
    public static void register() {
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:lang/en_us"),
                "Modify vanilla item names",
                context -> {
                    context.getFile().getAsJsonObject().addProperty("block.minecraft.stonecutter", "Blockcutter");
                    context.getFile().getAsJsonObject().addProperty("container.stonecutter", "Blockcutter");
                    context.getFile().getAsJsonObject().addProperty("stat.minecraft.interact_with_stonecutter", "Interactions with Blockcutter");
                    context.getFile().getAsJsonObject().addProperty("subtitles.ui.stonecutter.take_result", "Blockcutter used");
                    context.getFile().getAsJsonObject().addProperty("block.minecraft.chain", "Iron Chain");
                    context.getFile().getAsJsonObject().addProperty("block.minecraft.lantern", "Iron Lantern");
                },
                false
        );
    }
}
