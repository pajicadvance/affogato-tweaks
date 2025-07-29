package me.pajic.affogatotweaks.mixson;

import com.google.gson.JsonElement;
import me.pajic.affogatotweaks.values.XpValues;
import net.ramixin.mixson.inline.Mixson;

import java.util.Set;

public class RecipeEvents {
    private static final Set<String> REMOVED_RECIPES = Set.of(
            "storagedrawers:recipe/obsidian_storage_upgrade",
            "storagedrawers:recipe/iron_storage_upgrade",
            "storagedrawers:recipe/gold_storage_upgrade",
            "storagedrawers:recipe/diamond_storage_upgrade",
            "storagedrawers:recipe/emerald_storage_upgrade",
            "storagedrawers:recipe/void_upgrade",
            "storagedrawers:recipe/conversion_upgrade",
            "storagedrawers:recipe/illumination_upgrade",
            "storagedrawers:recipe/portability_upgrade",
            "inmis:recipe/baby_backpack",
            "inmis:recipe/endless_backpack",
            "inmis:recipe/withered_backpack",
            "inmis:recipe/blazing_backpack",
            "inmis:recipe/ender_pouch",
            "minecraft:recipe/cake",
            "farmersdelight:recipe/cake_from_milk_bottle",
            "farmersdelight:recipe/cutting/gravel"
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
                            if (XpValues.RECIPE_RESULT_TO_XP.containsKey(resultItem))
                                context.getFile().getAsJsonObject().addProperty("experience", XpValues.RECIPE_RESULT_TO_XP.getDouble(resultItem));
                            else {
                                JsonElement inputItems = context.getFile().getAsJsonObject().getAsJsonObject("ingredient");
                                if (!inputItems.isJsonArray() && inputItems.getAsJsonObject().has("item")) {
                                    String inputItem = inputItems.getAsJsonObject().getAsJsonPrimitive("item").getAsString();
                                    if (XpValues.RECIPE_INPUT_TO_XP.containsKey(inputItem))
                                        context.getFile().getAsJsonObject().addProperty("experience", XpValues.RECIPE_INPUT_TO_XP.getDouble(inputItem));
                                    else context.getFile().getAsJsonObject().remove("experience");
                                }
                                else context.getFile().getAsJsonObject().remove("experience");
                            }
                        }
                    }
                },
                true
        );
        REMOVED_RECIPES.forEach(s -> Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals(s),
                "Remove recipes",
                context -> context.markForDeletion(true),
                true
        ));
    }
}
