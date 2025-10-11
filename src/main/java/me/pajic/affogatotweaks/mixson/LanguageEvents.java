package me.pajic.affogatotweaks.mixson;

import net.ramixin.mixson.inline.Mixson;

public class LanguageEvents {
    public static void register() {
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("minecraft:lang/en_us"),
                "Modify vanilla item names",
                context -> {
                    context.getFile().getAsJsonObject().remove("block.minecraft.stonecutter");
                    context.getFile().getAsJsonObject().remove("container.stonecutter");
                    context.getFile().getAsJsonObject().remove("stat.minecraft.interact_with_stonecutter");
                    context.getFile().getAsJsonObject().remove("subtitles.ui.stonecutter.take_result");
                    context.getFile().getAsJsonObject().remove("block.minecraft.chain");
                    context.getFile().getAsJsonObject().remove("block.minecraft.lantern");
                },
                false
        );
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals("item_descriptions:lang/en_us"),
                "Modify item descriptions",
                context -> {
                    context.getFile().getAsJsonObject().remove("lore.minecraft.lantern");
                    context.getFile().getAsJsonObject().remove("lore.minecraft.stonecutter");
                },
                false
        );
    }
}
