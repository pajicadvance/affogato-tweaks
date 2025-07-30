package me.pajic.affogatotweaks.values;

import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.world.item.ArmorItem;

import java.util.Map;

public class ArmorDefenseValues {
    public static final Object2IntMap<ArmorItem.Type> LEATHER_ARMOR = new Object2IntArrayMap<>(Map.of(
            ArmorItem.Type.HELMET, 1,
            ArmorItem.Type.CHESTPLATE, 2,
            ArmorItem.Type.LEGGINGS, 2,
            ArmorItem.Type.BOOTS, 1,
            ArmorItem.Type.BODY, 2
    ));
    public static final Object2IntMap<ArmorItem.Type> IRON_ARMOR =  new Object2IntArrayMap<>(Map.of(
            ArmorItem.Type.HELMET, 2,
            ArmorItem.Type.CHESTPLATE, 5,
            ArmorItem.Type.LEGGINGS, 3,
            ArmorItem.Type.BOOTS, 2,
            ArmorItem.Type.BODY, 5
    ));
    public static final Object2IntMap<ArmorItem.Type> GOLD_ARMOR = new Object2IntArrayMap<>(Map.of(
            ArmorItem.Type.HELMET, 1,
            ArmorItem.Type.CHESTPLATE, 3,
            ArmorItem.Type.LEGGINGS, 3,
            ArmorItem.Type.BOOTS, 1,
            ArmorItem.Type.BODY, 3
    ));
    public static final Object2IntMap<ArmorItem.Type> NETHERITE_ARMOR = new Object2IntArrayMap<>(Map.of(
            ArmorItem.Type.HELMET, 2,
            ArmorItem.Type.CHESTPLATE, 7,
            ArmorItem.Type.LEGGINGS, 5,
            ArmorItem.Type.BOOTS, 2,
            ArmorItem.Type.BODY, 7
    ));
}
