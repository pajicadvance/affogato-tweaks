package me.pajic.affogato_core.mixson.events;

import me.pajic.affogato_core.mixson.MixsonHelper;

public class LanguageEvents {
    public static void register() {
        MixsonHelper.registerSingleJson(
                "Modify vanilla item names",
                "minecraft:lang/en_us",
                context -> {
                    context.getFile().getAsJsonObject().remove("block.minecraft.stonecutter");
                    context.getFile().getAsJsonObject().remove("container.stonecutter");
                    context.getFile().getAsJsonObject().remove("stat.minecraft.interact_with_stonecutter");
                    context.getFile().getAsJsonObject().remove("subtitles.ui.stonecutter.take_result");
                }
        );
        MixsonHelper.registerSingleJson(
                "Modify item descriptions",
                "item_descriptions:lang/en_us",
                context -> {
                    context.getFile().getAsJsonObject().remove("lore.minecraft.lantern");
                    context.getFile().getAsJsonObject().remove("lore.minecraft.stonecutter");
                    context.getFile().getAsJsonObject().remove("lore.minecraft.compass");
                    context.getFile().getAsJsonObject().remove("lore.minecraft.clock");
                    context.getFile().getAsJsonObject().remove("lore.minecraft.totem_of_undying");
                    context.getFile().getAsJsonObject().remove("lore.minecraft.spyglass");
                    context.getFile().getAsJsonObject().remove("lore.minecraft.ender_chest");
                    context.getFile().getAsJsonObject().remove("lore.minecraft.arrow");
                    context.getFile().getAsJsonObject().remove("lore.minecraft.spectral_arrow");
                    context.getFile().getAsJsonObject().remove("lore.minecraft.tipped_arrow");
                    context.getFile().getAsJsonObject().remove("lore.minecraft.recovery_compass");
                    context.getFile().getAsJsonObject().remove("lore.minecraft.elytra");
                    context.getFile().getAsJsonObject().remove("tag.c.shulker_boxes.description");
                    context.getFile().getAsJsonObject().remove("tag.c.elytra.description");
                }
        );
    }
}
