package me.pajic.affogatotweaks;

import dev.emi.emi.EmiPort;
import dev.emi.emi.EmiUtil;
import dev.emi.emi.api.EmiInitRegistry;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiCraftingRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.recipe.EmiAnvilRecipe;
import dev.emi.emi.recipe.EmiBrewingRecipe;
import me.pajic.affogatotweaks.oxidation.OxidationData;
import me.pajic.affogatotweaks.oxidation.OxidationUtil;
import me.pajic.affogatotweaks.values.MiscValues;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class EMIPlugin implements EmiPlugin {

    @Override
    public void initialize(EmiInitRegistry registry) {
        MiscValues.HIDDEN_ITEMS.forEach(s ->
                registry.disableStacks(stack ->
                        MiscValues.HIDDEN_ITEMS.contains(BuiltInRegistries.ITEM.getKey(stack.getItemStack().getItem()).toString())
                )
        );
    }

    @Override
    public void register(EmiRegistry registry) {
        registry.addRecipe(new EmiAnvilRecipe(
                EmiStack.of(Items.TRIDENT),
                EmiStack.of(Items.PRISMARINE_SHARD),
                EmiPort.id(
                        "emi",
                        "/" + "anvil/repairing/material" +
                                "/" + EmiUtil.subId(Items.TRIDENT) +
                                "/" + EmiUtil.subId(Items.PRISMARINE_SHARD)
                )
        ));
        registry.addRecipe(new EmiAnvilRecipe(
                EmiStack.of(Items.MACE),
                EmiStack.of(Items.BREEZE_ROD),
                EmiPort.id(
                        "emi",
                        "/" + "anvil/repairing/material" +
                                "/" + EmiUtil.subId(Items.MACE) +
                                "/" + EmiUtil.subId(Items.BREEZE_ROD)
                )
        ));
        registry.addRecipe(new EmiBrewingRecipe(
                EmiStack.of(Items.SPLASH_POTION),
                EmiStack.of(Items.EMERALD),
                EmiStack.of(Items.EXPERIENCE_BOTTLE),
                EmiPort.id(
                        "emi",
                        "/" + "brewing/item" +
                                "/" + EmiUtil.subId(Items.EXPERIENCE_BOTTLE)
                )
        ));
        EmiPort.getItemRegistry().getTagOrEmpty(OxidationData.OXIDIZABLE).forEach(itemHolder -> {
            Item item = itemHolder.value();
            EmiStack itemStack = EmiStack.of(item);
            ItemStack waxedItem = new ItemStack(item);
            EmiIngredient axes = EmiIngredient.of(ItemTags.AXES);
            EmiStack honeycomb = EmiStack.of(Items.HONEYCOMB);
            waxedItem.set(OxidationData.WAXED, true);
            EmiStack waxedItemStack = EmiStack.of(waxedItem);

            registry.addRecipe(new EmiCraftingRecipe(
                    List.of(itemStack, honeycomb), waxedItemStack,
                    Main.withModNamespace("/item_waxing/" + EmiUtil.subId(item))
            ));
            registry.addRecipe(new EmiCraftingRecipe(
                    List.of(waxedItemStack, axes), itemStack,
                    Main.withModNamespace("/item_axing/" + EmiUtil.subId(item))
            ));

            for (int i = 1; i <= 3; i++) {
                ItemStack currentStage = new ItemStack(item);
                OxidationUtil.incrementItemOxidation(currentStage, i);
                ItemStack updatedStage = new ItemStack(item);
                if (i > 1) OxidationUtil.incrementItemOxidation(updatedStage, i - 1);
                EmiStack currentStageStack = EmiStack.of(currentStage);
                EmiStack updatedStageStack = EmiStack.of(updatedStage);

                registry.addRecipe(new EmiCraftingRecipe(
                        List.of(currentStageStack, axes), updatedStageStack,
                        Main.withModNamespace("/item_axing/" + EmiUtil.subId(item) + "_stage_" + i)
                ));
            }
        });
    }
}
