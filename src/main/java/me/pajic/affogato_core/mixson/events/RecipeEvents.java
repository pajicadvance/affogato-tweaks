package me.pajic.affogato_core.mixson.events;

import com.google.gson.JsonElement;
import me.pajic.affogato_core.CompatFlags;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.mixson.MixsonHelper;

import java.util.List;

public class RecipeEvents {

    private static final List<String> EARLY_GAME_DISABLED_TOOLS = List.of(
            "minecraft:recipe/stone_pickaxe",
            "minecraft:recipe/stone_shovel",
            "minecraft:recipe/stone_sword",
            "minecraft:recipe/stone_axe",
            "minecraft:recipe/stone_hoe",
            "minecraft:recipe/stone_spear",
            "minecraft:recipe/wooden_pickaxe",
            "minecraft:recipe/wooden_shovel",
            "minecraft:recipe/wooden_sword",
            "minecraft:recipe/wooden_axe",
            "minecraft:recipe/wooden_hoe",
            "minecraft:recipe/wooden_spear"
    );

    public static void register() {
        MixsonHelper.registerMultiJson(
                "Modify smelting and blasting recipe XP rewards",
                index -> index.id().getPath().startsWith("recipe/") && !index.id().getNamespace().equals("emi"),
                context -> {
                    String type = context.getFile().getAsJsonObject().getAsJsonPrimitive("type").getAsString();
                    if (context.getFile().getAsJsonObject().has("experience")) {
                        if (type.equals("minecraft:campfire_cooking") || type.equals("minecraft:smoking"))
                            context.getFile().getAsJsonObject().remove("experience");
                        if (type.equals("minecraft:smelting") || type.equals("minecraft:blasting")) {
                            String resultItem = context.getFile().getAsJsonObject().getAsJsonObject("result").getAsJsonPrimitive("id").getAsString();
                            if (Main.CONFIG.experience.recipeXpSwapByResult.get().containsKey(resultItem))
                                context.getFile().getAsJsonObject().addProperty("experience", Main.CONFIG.experience.recipeXpSwapByResult.get().get(resultItem));
                            else {
                                JsonElement inputItems = context.getFile().getAsJsonObject().get("ingredient");
                                if (!inputItems.isJsonArray()) {
                                    String inputItem = inputItems.getAsString();
                                    if (Main.CONFIG.experience.recipeXpSwapByInput.get().containsKey(inputItem))
                                        context.getFile().getAsJsonObject().addProperty("experience", Main.CONFIG.experience.recipeXpSwapByInput.get().get(inputItem));
                                    else context.getFile().getAsJsonObject().remove("experience");
                                }
                                else context.getFile().getAsJsonObject().remove("experience");
                            }
                        }
                    }
                }
        );
        Main.CONFIG.removedRecipes.get().forEach(s -> MixsonHelper.registerSingleJson(
                "Remove recipes",
                s,
                context -> context.markForDeletion(true)
        ));
        if (CompatFlags.FD_LOADED && Main.CONFIG.features.affogatoRecipeEdits.get()) MixsonHelper.registerSingleJson(
                "Remove cake recipe",
                "minecraft:recipe/cake",
                context -> context.markForDeletion(true)
        );
        if (Main.CONFIG.features.affogatoEarlyGameChanges.get()) EARLY_GAME_DISABLED_TOOLS.forEach(s -> MixsonHelper.registerSingleJson(
                "Remove recipes",
                s,
                context -> context.markForDeletion(true)
        ));
    }
}
