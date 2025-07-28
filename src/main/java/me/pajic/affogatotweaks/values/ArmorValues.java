package me.pajic.affogatotweaks.values;

import net.minecraft.world.item.ArmorItem;

import java.util.Map;

public class ArmorValues {
    public static final Map<ArmorItem.Type, Integer> LEATHER_ARMOR = Map.of(
            ArmorItem.Type.HELMET, 1,
            ArmorItem.Type.CHESTPLATE, 2,
            ArmorItem.Type.LEGGINGS, 2,
            ArmorItem.Type.BOOTS, 1,
            ArmorItem.Type.BODY, 2
    );
    public static final Map<ArmorItem.Type, Integer> IRON_ARMOR = Map.of(
            ArmorItem.Type.HELMET, 2,
            ArmorItem.Type.CHESTPLATE, 5,
            ArmorItem.Type.LEGGINGS, 3,
            ArmorItem.Type.BOOTS, 2,
            ArmorItem.Type.BODY, 5
    );
    public static final Map<ArmorItem.Type, Integer> GOLD_ARMOR = Map.of(
            ArmorItem.Type.HELMET, 1,
            ArmorItem.Type.CHESTPLATE, 3,
            ArmorItem.Type.LEGGINGS, 3,
            ArmorItem.Type.BOOTS, 1,
            ArmorItem.Type.BODY, 3
    );
    public static final Map<ArmorItem.Type, Integer> NETHERITE_ARMOR = Map.of(
            ArmorItem.Type.HELMET, 2,
            ArmorItem.Type.CHESTPLATE, 7,
            ArmorItem.Type.LEGGINGS, 5,
            ArmorItem.Type.BOOTS, 2,
            ArmorItem.Type.BODY, 7
    );

    public static final float DIAMOND_TOUGHNESS = 0.0F;
    public static final float NETHERITE_TOUGHNESS = 4.0F;
}
