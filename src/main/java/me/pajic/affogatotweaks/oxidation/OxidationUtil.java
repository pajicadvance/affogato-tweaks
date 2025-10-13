package me.pajic.affogatotweaks.oxidation;

import it.unimi.dsi.fastutil.objects.ObjectBooleanImmutablePair;
import it.unimi.dsi.fastutil.objects.ObjectBooleanPair;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

public class OxidationUtil {

    public static ObjectBooleanPair<ItemStack> tryOxidize(ItemStack stack, long worldTime, RandomSource random, boolean copy) {
        if (stack.is(OxidationData.OXIDIZABLE) && !stack.has(OxidationData.WAXED) && worldTime % 1200 == 0) {
            int oxidation = getItemOxidation(stack);
            if (oxidation < 3) {
                float chanceModifier = stack.isDamageableItem() ? Mth.clampedMap(
                        stack.getMaxDamage() - stack.getDamageValue(),
                        stack.getMaxDamage() * ((float) (2 - oxidation) / 4F),
                        stack.getMaxDamage() * ((float) (4 - oxidation) / 4F),
                        0.5F, 2
                ) : 1;
                if (random.nextFloat() < (32F / 1125) / chanceModifier) {
                    if (copy) {
                        ItemStack updatedStack = stack.copy();
                        incrementItemOxidation(updatedStack, 1);
                        return new ObjectBooleanImmutablePair<>(updatedStack, true);
                    } else {
                        incrementItemOxidation(stack, 1);
                    }
                }
            }
        }
        return new ObjectBooleanImmutablePair<>(stack, false);
    }

    public static int getItemOxidation(ItemStack stack) {
        return stack.getOrDefault(OxidationData.OXIDATION, 0);
    }

    public static void incrementItemOxidation(ItemStack stack, int increment) {
        int updatedOxidation = getItemOxidation(stack) + increment;
        if (updatedOxidation <= 0) stack.remove(OxidationData.OXIDATION);
        else stack.set(OxidationData.OXIDATION, Mth.clamp(updatedOxidation, 1, 3));
    }
}
