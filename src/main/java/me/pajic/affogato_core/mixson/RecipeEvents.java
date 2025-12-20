package me.pajic.affogato_core.mixson;

import com.google.gson.JsonElement;
import me.pajic.affogato_core.CompatFlags;
import me.pajic.affogato_core.Main;
import net.ramixin.mixson.inline.Mixson;

import java.util.List;

public class RecipeEvents {

    private static final List<String> STONE_TOOLS = List.of(
            "minecraft:recipe/stone_pickaxe",
            "minecraft:recipe/stone_shovel",
            "minecraft:recipe/stone_sword",
            "minecraft:recipe/stone_axe",
            "minecraft:recipe/stone_hoe"
    );

    public static void register() {
        Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.getPath().startsWith("recipe/") && !rl.getNamespace().equals("emi"),
                "Modify smelting and blasting recipe XP rewards",
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
                },
                false
        );
        Main.CONFIG.removedRecipes.get().forEach(s -> Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals(s),
                "Remove recipes",
                context -> context.markForDeletion(true),
                false
        ));
        if (CompatFlags.FD_LOADED && Main.CONFIG.features.affogatoRecipeEdits.get()) Main.CONFIG.removedRecipes.get().forEach(s ->
                Mixson.registerEvent(
                        Mixson.DEFAULT_PRIORITY,
                        rl -> rl.toString().equals("minecraft:recipe/cake"),
                        "Remove cake recipe",
                        context -> context.markForDeletion(true),
                        false
                )
        );
        if (Main.CONFIG.features.stoneToolNuke.get()) STONE_TOOLS.forEach(s -> Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals(s),
                "Remove recipes",
                context -> context.markForDeletion(true),
                false
        ));
    }
}
