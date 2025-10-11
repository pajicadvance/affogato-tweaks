package me.pajic.affogatotweaks;

import dev.emi.emi.EmiPort;
import dev.emi.emi.EmiUtil;
import dev.emi.emi.api.EmiInitRegistry;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.recipe.EmiAnvilRecipe;
import me.pajic.affogatotweaks.values.MiscValues;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;

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
    }
}
