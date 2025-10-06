package me.pajic.affogatotweaks.mixson;

import com.google.gson.JsonElement;
import me.pajic.affogatotweaks.values.MiscValues;
import me.pajic.affogatotweaks.values.XpValues;
import net.ramixin.mixson.inline.Mixson;

public class RecipeEvents {

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
        MiscValues.REMOVED_RECIPES.forEach(s -> Mixson.registerEvent(
                Mixson.DEFAULT_PRIORITY,
                rl -> rl.toString().equals(s),
                "Remove recipes",
                context -> context.markForDeletion(true),
                true
        ));
    }
}
