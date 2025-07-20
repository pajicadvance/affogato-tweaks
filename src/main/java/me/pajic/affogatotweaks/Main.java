package me.pajic.affogatotweaks;

import me.emafire003.dev.custombrewrecipes.CustomBrewRecipeRegister;
import me.pajic.affogatotweaks.mixson.MixsonInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.levelgen.structure.Structure;

public class Main implements ModInitializer {
    public static final String MOD_ID = "affogatotweaks";
    public static final TagKey<Structure> OUTPOSTS = TagKey.create(
            Registries.STRUCTURE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "outposts")
    );

    @Override
    public void onInitialize() {
        // data patches
        MixsonInitializer.init();
        // guide book
        /*LavenderBookItem.registerForBook(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "affogato_guide"),
                new Item.Properties().stacksTo(1)
        );*/
        // xp bottle brewing recipe
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
        // custom cake recipe
        if (FabricLoader.getInstance().isModLoaded("farmersdelight"))
            FabricLoader.getInstance().getModContainer(Main.MOD_ID).ifPresent(modContainer ->
                    ResourceManagerHelper.registerBuiltinResourcePack(
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "cake_recipe"),
                            modContainer, ResourcePackActivationType.ALWAYS_ENABLED
                    )
            );
        // handle rs outposts for raids
        if (FabricLoader.getInstance().isModLoaded("repurposed_structures"))
            FabricLoader.getInstance().getModContainer(Main.MOD_ID).ifPresent(modContainer ->
                    ResourceManagerHelper.registerBuiltinResourcePack(
                            ResourceLocation.fromNamespaceAndPath(MOD_ID, "rs_outpost_tag"),
                            modContainer, ResourcePackActivationType.ALWAYS_ENABLED
                    )
            );
    }
}
