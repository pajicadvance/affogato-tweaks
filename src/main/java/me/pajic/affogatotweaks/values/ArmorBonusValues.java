package me.pajic.affogatotweaks.values;

import it.unimi.dsi.fastutil.objects.Object2FloatArrayMap;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;

import java.util.Map;

public class ArmorBonusValues {

    public static final Object2FloatMap<Holder<ArmorMaterial>> TOUGHNESS = new Object2FloatArrayMap<>(Map.of(
            ArmorMaterials.DIAMOND, 2.0F,
            ArmorMaterials.NETHERITE, 4.0F
    ));

    public static final Object2FloatMap<Holder<ArmorMaterial>> KNOCKBACK_RESIST = new Object2FloatArrayMap<>(Map.of(
            ArmorMaterials.IRON, 0.025F,
            ArmorMaterials.CHAIN, 0.025F,
            ArmorMaterials.GOLD, 0.01F,
            ArmorMaterials.DIAMOND, 0.05F
    ));
}
