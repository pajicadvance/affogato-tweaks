package me.pajic.affogatotweaks.potion;

import me.emafire003.dev.custombrewrecipes.CustomBrewRecipeRegister;
import me.pajic.affogatotweaks.Main;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

public class ModPotions {
    public static final Potion ABSORPTION = Registry.register(
            BuiltInRegistries.POTION,
            ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "absorption"),
            new Potion(
                    "absorption",
                    new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0)
            )
    );
    public static final Potion LONG_ABSORPTION = Registry.register(
            BuiltInRegistries.POTION,
            ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "long_absorption"),
            new Potion(
                    "absorption",
                    new MobEffectInstance(MobEffects.ABSORPTION, 4800, 0)
            )
    );
    public static final Potion STRONG_ABSORPTION = Registry.register(
            BuiltInRegistries.POTION,
            ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "strong_absorption"),
            new Potion(
                    "absorption",
                    new MobEffectInstance(MobEffects.ABSORPTION, 1200, 1)
            )
    );
    public static final Potion LIFE_ELIXIR = Registry.register(
            BuiltInRegistries.POTION,
            ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "life_elixir"),
            new Potion(
                    "life_elixir",
                    new MobEffectInstance(MobEffects.REGENERATION, 400, 1),
                    new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 0),
                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0),
                    new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3)
            )
    );

    public static boolean isLifeElixir(ItemStack stack) {
        PotionContents potionContents = stack.get(DataComponents.POTION_CONTENTS);
        return potionContents != null && potionContents.potion().orElse(Potions.WATER).is(ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "life_elixir"));
    }

    public static void init() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(
                    Potions.AWKWARD,
                    Ingredient.of(Items.GOLDEN_APPLE),
                    BuiltInRegistries.POTION.wrapAsHolder(ABSORPTION)
            );
            builder.registerPotionRecipe(
                    BuiltInRegistries.POTION.wrapAsHolder(ABSORPTION),
                    Ingredient.of(Items.REDSTONE),
                    BuiltInRegistries.POTION.wrapAsHolder(LONG_ABSORPTION)
            );
            builder.registerPotionRecipe(
                    BuiltInRegistries.POTION.wrapAsHolder(ABSORPTION),
                    Ingredient.of(Items.GLOWSTONE_DUST),
                    BuiltInRegistries.POTION.wrapAsHolder(STRONG_ABSORPTION)
            );
            builder.registerPotionRecipe(
                    Potions.AWKWARD,
                    Ingredient.of(Items.ENCHANTED_GOLDEN_APPLE),
                    BuiltInRegistries.POTION.wrapAsHolder(LIFE_ELIXIR)
            );
        });
        CustomBrewRecipeRegister.registerCustomRecipeWithComponents(
                Items.SPLASH_POTION,
                Items.EMERALD,
                Items.EXPERIENCE_BOTTLE,
                DataComponentMap.builder().set(
                        DataComponents.POTION_CONTENTS,
                        new PotionContents(Potions.AWKWARD)
                ).build(),
                null,
                null
        );
    }
}
