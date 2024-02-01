package me.pajic.affogatotweaks.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;

public class EnchantmentUtil {

    public static List<Enchantment> ENCHANTMENT_BLACKLIST;
    private static final int[] levelPool = new int[]{1, 1, 1, 1, 1, 1, 2, 2, 2, 3};

    public static boolean preventEnchantmentAdditionToList(List<EnchantmentLevelEntry> list, EnchantmentLevelEntry entry) {

        if (ENCHANTMENT_BLACKLIST.contains(entry.enchantment)) {
            return false;
        }
        else {
            int enchantmentLevel = returnEnchantmentLevelFromPool(Random.create(MinecraftClient.getInstance().getServer().getWorld(World.OVERWORLD).getSeed()), entry.enchantment.getMaxLevel());
            return list.add(new EnchantmentLevelEntry(entry.enchantment, enchantmentLevel));
        }
    }

    public static List<Enchantment> removeEnchantmentsFromList(List<Enchantment> list) {
        return list.stream().filter(enchantment -> !EnchantmentUtil.ENCHANTMENT_BLACKLIST.contains(enchantment)).toList();
    }

    public static int returnEnchantmentLevelFromPool(Random random, int max) {
        if (max >= 3) return EnchantmentUtil.levelPool[random.nextInt(10)];
        if (max == 2) return EnchantmentUtil.levelPool[random.nextInt(9)];
        return 1;
    }
}
