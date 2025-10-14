package me.pajic.affogatotweaks.values;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import me.pajic.affogatotweaks.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;

import java.util.Map;

public class DurabilityValues {
    public static final int HELMET_BASE = 13;
    public static final int BODY_BASE = 16;
    public static final int LEGGINGS_BASE = 15;
    public static final int BOOTS_BASE = 12;

    public static final int LEATHER_ARMOR_MULT = 20;
    public static final int COPPER_ARMOR_MULT = 12;
    public static final int IRON_ARMOR_MULT = 24;
    public static final int GOLDEN_ARMOR_MULT = 8;
    public static final int DIAMOND_ARMOR_MULT = 36;
    public static final int NETHERITE_ARMOR_MULT = 72;
    public static final int TURTLE_ARMOR_MULT = 20;
    public static final int WOLF_ARMOR_MULT = 20;
    public static final Object2IntMap<Holder<ArmorMaterial>> ARMOR_DURABILITY_MULTS = new Object2IntOpenHashMap<>(Map.of(
            ArmorMaterials.LEATHER, LEATHER_ARMOR_MULT,
            ModItems.COPPER, COPPER_ARMOR_MULT,
            ArmorMaterials.IRON, IRON_ARMOR_MULT,
            ArmorMaterials.GOLD, GOLDEN_ARMOR_MULT,
            ArmorMaterials.DIAMOND, DIAMOND_ARMOR_MULT,
            ArmorMaterials.NETHERITE, NETHERITE_ARMOR_MULT,
            ArmorMaterials.TURTLE, TURTLE_ARMOR_MULT,
            ArmorMaterials.ARMADILLO, WOLF_ARMOR_MULT
    ));

    public static final int WOODEN_TOOL = 80;
    public static final int STONE_TOOL = 96;
    public static final int COPPER_TOOL = 160;
    public static final int IRON_TOOL = 320;
    public static final int GOLDEN_TOOL = 80;
    public static final int DIAMOND_TOOL = 1280;
    public static final int NETHERITE_TOOL = 2560;

    public static final int BOW = 256;
    public static final int ELYTRA = 640;
    public static final int SHEARS = 256;
    public static final int SHIELD = 384;
    public static final int CROSSBOW = 320;
    public static final int TRIDENT = 384;
}
