package me.pajic.affogatotweaks;

import dev.emi.emi.api.EmiInitRegistry;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import me.pajic.affogatotweaks.values.MiscValues;
import net.minecraft.core.registries.BuiltInRegistries;

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
    public void register(EmiRegistry emiRegistry) {}
}
