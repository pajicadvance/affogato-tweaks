package me.pajic.affogatotweaks.oxidation;

import com.mojang.serialization.Codec;
import me.pajic.affogatotweaks.Main;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public class OxidationData {

    public static final DataComponentType<Integer> OXIDATION = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Main.withModNamespace("oxidation"),
            DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );
    public static final DataComponentType<Boolean> WAXED = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Main.withModNamespace("waxed"),
            DataComponentType.<Boolean>builder().persistent(Codec.BOOL).build()
    );

    public static final TagKey<Item> OXIDIZABLE = TagKey.create(
            Registries.ITEM,
            Main.withModNamespace("oxidizable")
    );

    public static RecipeSerializer<ItemWaxingRecipe> ITEM_WAXING = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            "crafting_special_item_waxing",
            new SimpleCraftingRecipeSerializer<>(ItemWaxingRecipe::new)
    );
    public static RecipeSerializer<ItemAxingRecipe> ITEM_AXING = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            "crafting_special_item_axing",
            new SimpleCraftingRecipeSerializer<>(ItemAxingRecipe::new)
    );

    public static void init() {}
}
