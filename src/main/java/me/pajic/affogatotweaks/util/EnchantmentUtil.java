package me.pajic.affogatotweaks.util;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

public class EnchantmentUtil {

    public static List<Enchantment> ENCHANTMENT_BLACKLIST = new ArrayList<>();
    private static final int[] levelPoolMax5 = new int[]{1, 1, 1, 1, 1, 1, 2, 2, 2, 3};
    private static final int[] levelPoolMax4 = new int[]{1, 1, 1, 1, 2};

    public static boolean preventEnchantmentAdditionToList(List<EnchantmentInstance> list, EnchantmentInstance entry) {
        if (ENCHANTMENT_BLACKLIST.contains(entry.enchantment)) {
            return false;
        }
        else {
            return list.add(new EnchantmentInstance(entry.enchantment, returnEnchantmentLevelFromPool(
                    RandomSource.create(), entry.enchantment.getMaxLevel())));
        }
    }

    public static List<Enchantment> removeEnchantmentsFromList(List<Enchantment> list) {
        return list.stream().filter(enchantment -> !EnchantmentUtil.ENCHANTMENT_BLACKLIST.contains(enchantment)).toList();
    }

    public static int returnEnchantmentLevelFromPool(RandomSource random, int max) {
        switch (max) {
            case 5 -> {
                return EnchantmentUtil.levelPoolMax5[random.nextInt(10)];
            }
            case 4 -> {
                return EnchantmentUtil.levelPoolMax4[random.nextInt(5)];
            }
            default -> {
                return 1;
            }
        }
    }
}
