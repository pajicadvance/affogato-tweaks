package me.pajic.affogatotweaks.values;

import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.world.item.ArmorItem;

import java.util.Map;

public class ArmorDefenseValues {
    public static final float MAX_ARMOR_MULT = 2F;

    public static final Object2IntMap<ArmorItem.Type> LEATHER_ARMOR = new Object2IntArrayMap<>(Map.of(
            ArmorItem.Type.HELMET, 2,
            ArmorItem.Type.CHESTPLATE, 3,
            ArmorItem.Type.LEGGINGS, 2,
            ArmorItem.Type.BOOTS, 1,
            ArmorItem.Type.BODY, 6
    ));
    public static final Object2IntMap<ArmorItem.Type> COPPER_ARMOR =  new Object2IntArrayMap<>(Map.of(
            ArmorItem.Type.HELMET, 3,
            ArmorItem.Type.CHESTPLATE, 6,
            ArmorItem.Type.LEGGINGS, 5,
            ArmorItem.Type.BOOTS, 2,
            ArmorItem.Type.BODY, 13
    ));
    public static final Object2IntMap<ArmorItem.Type> GOLD_ARMOR = new Object2IntArrayMap<>(Map.of(
            ArmorItem.Type.HELMET, 3,
            ArmorItem.Type.CHESTPLATE, 6,
            ArmorItem.Type.LEGGINGS, 5,
            ArmorItem.Type.BOOTS, 2,
            ArmorItem.Type.BODY, 13
    ));
    public static final Object2IntMap<ArmorItem.Type> IRON_ARMOR =  new Object2IntArrayMap<>(Map.of(
            ArmorItem.Type.HELMET, 5,
            ArmorItem.Type.CHESTPLATE, 8,
            ArmorItem.Type.LEGGINGS, 7,
            ArmorItem.Type.BOOTS, 4,
            ArmorItem.Type.BODY, 19
    ));
    public static final Object2IntMap<ArmorItem.Type> DIAMOND_ARMOR = new Object2IntArrayMap<>(Map.of(
            ArmorItem.Type.HELMET, 8,
            ArmorItem.Type.CHESTPLATE, 9,
            ArmorItem.Type.LEGGINGS, 8,
            ArmorItem.Type.BOOTS, 7,
            ArmorItem.Type.BODY, 26
    ));
    public static final Object2IntMap<ArmorItem.Type> NETHERITE_ARMOR = new Object2IntArrayMap<>(Map.of(
            ArmorItem.Type.HELMET, 9,
            ArmorItem.Type.CHESTPLATE, 12,
            ArmorItem.Type.LEGGINGS, 11,
            ArmorItem.Type.BOOTS, 8,
            ArmorItem.Type.BODY, 32
    ));
    public static final Object2IntMap<ArmorItem.Type> ARMADILLO_ARMOR = new Object2IntArrayMap<>(Map.of(
            ArmorItem.Type.HELMET, 9,
            ArmorItem.Type.CHESTPLATE, 12,
            ArmorItem.Type.LEGGINGS, 11,
            ArmorItem.Type.BOOTS, 8,
            ArmorItem.Type.BODY, 32
    ));
}
