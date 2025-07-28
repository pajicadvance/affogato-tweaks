package me.pajic.affogatotweaks.values;

import it.unimi.dsi.fastutil.ints.IntFloatImmutablePair;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;

import java.util.Set;

public class NutritionValues {
    public static final IntFloatImmutablePair CAKE_SLICE = new IntFloatImmutablePair(3, 0.3F);
    public static final IntFloatImmutablePair OMINOUS_BOTTLE = new IntFloatImmutablePair(0, 0);

    public static final Set<FoodProperties> REMOVED_FOODS = Set.of(
            Foods.GOLDEN_CARROT,
            Foods.GOLDEN_APPLE,
            Foods.ENCHANTED_GOLDEN_APPLE
    );
}
