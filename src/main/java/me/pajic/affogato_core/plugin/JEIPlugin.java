package me.pajic.affogato_core.plugin;

import me.pajic.affogato_core.Main;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    public static final List<String> TOOLS = List.of(
            "minecraft:stone_pickaxe",
            "minecraft:stone_shovel",
            "minecraft:stone_sword",
            "minecraft:stone_axe",
            "minecraft:stone_hoe",
            "minecraft:wooden_pickaxe",
            "minecraft:wooden_shovel",
            "minecraft:wooden_sword",
            "minecraft:wooden_axe",
            "minecraft:wooden_hoe"
    );

    @Override
    public void registerRecipes(@NonNull IRecipeRegistration registration) {
        Set<ItemStack> hidden = new HashSet<>();
        Main.CONFIG.hiddenItems.get().forEach(s ->
                BuiltInRegistries.ITEM.getOptional(Identifier.tryParse(s)).ifPresent(item -> hidden.add(new ItemStack(item)))
        );
        if (Main.CONFIG.features.affogatoEarlyGameChanges.get()) TOOLS.forEach(s ->
                BuiltInRegistries.ITEM.getOptional(Identifier.tryParse(s)).ifPresent(item -> hidden.add(new ItemStack(item)))
        );
        registration.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, hidden);
    }

    @Override
    public @NotNull Identifier getPluginUid() {
        return Main.id("jei_plugin");
    }
}
