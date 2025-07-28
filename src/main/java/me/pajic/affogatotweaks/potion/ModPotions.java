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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

public class ModPotions {
    public static final Potion VITALITY_POTION = Registry.register(
            BuiltInRegistries.POTION,
            ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "vitality"),
            new Potion(
                    "vitality",
                    new MobEffectInstance(MobEffects.REGENERATION, 100, 1),
                    new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0)
            )
    );
    public static final Potion REVITALIZATION_POTION = Registry.register(
            BuiltInRegistries.POTION,
            ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "revitalization"),
            new Potion(
                    "revitalization",
                    new MobEffectInstance(MobEffects.REGENERATION, 400, 1),
                    new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 0),
                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0),
                    new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3)
            )
    );

    public static void init() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(
                    Potions.AWKWARD,
                    Ingredient.of(Items.GOLDEN_APPLE),
                    BuiltInRegistries.POTION.wrapAsHolder(VITALITY_POTION)
            );
            builder.registerPotionRecipe(
                    Potions.AWKWARD,
                    Ingredient.of(Items.ENCHANTED_GOLDEN_APPLE),
                    BuiltInRegistries.POTION.wrapAsHolder(REVITALIZATION_POTION)
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
