package me.pajic.affogatotweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.EnumMap;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;

@Mixin(value = ArmorMaterials.class)
public class ArmorMaterialsMixin {

    // Modifies the base armor durabilities for each armor part
    @Inject(method = "method_48405", at = @At("HEAD"),cancellable = true)
    private static void setBaseDurabilities(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.put(ArmorItem.Type.HELMET, 13);
        map.put(ArmorItem.Type.LEGGINGS, 15);
        map.put(ArmorItem.Type.CHESTPLATE, 16);
        map.put(ArmorItem.Type.BOOTS, 12);
    }

    // Modifies the armor ratings for each material type
    @Inject(method = "method_48406", at = @At("HEAD"), cancellable = true)
    private static void setNetheriteArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.put(ArmorItem.Type.HELMET, 2);
        map.put(ArmorItem.Type.CHESTPLATE, 7);
        map.put(ArmorItem.Type.LEGGINGS, 5);
        map.put(ArmorItem.Type.BOOTS, 2);
    }

    @Inject(method = "method_48409", at = @At("HEAD"), cancellable = true)
    private static void setGoldArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.put(ArmorItem.Type.HELMET, 1);
        map.put(ArmorItem.Type.CHESTPLATE, 3);
        map.put(ArmorItem.Type.LEGGINGS, 3);
        map.put(ArmorItem.Type.BOOTS, 1);
    }

    @Inject(method = "method_48410", at = @At("HEAD"), cancellable = true)
    private static void setIronArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.put(ArmorItem.Type.HELMET, 2);
        map.put(ArmorItem.Type.CHESTPLATE, 5);
        map.put(ArmorItem.Type.LEGGINGS, 3);
        map.put(ArmorItem.Type.BOOTS, 2);
    }

    @Inject(method = "method_48411", at = @At("HEAD"), cancellable = true)
    private static void setChainArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.put(ArmorItem.Type.HELMET, 2);
        map.put(ArmorItem.Type.CHESTPLATE, 5);
        map.put(ArmorItem.Type.LEGGINGS, 3);
        map.put(ArmorItem.Type.BOOTS, 2);
    }

    @Inject(method = "method_48412", at = @At("HEAD"), cancellable = true)
    private static void setLeatherArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.put(ArmorItem.Type.HELMET, 1);
        map.put(ArmorItem.Type.CHESTPLATE, 2);
        map.put(ArmorItem.Type.LEGGINGS, 2);
        map.put(ArmorItem.Type.BOOTS, 1);
    }

    // Modifies the armor toughness values for certain material types
    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 2.0F))
    private static float setDiamondToughness(float constant) {
        return 0.0F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 3.0F))
    private static float setNetheriteToughness(float constant) {
        return 4.0F;
    }

    // Modifies the armor durability multiplier for each material type
    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 37))
    private static int setNetheriteDurabilityMultiplier(int constant) {
        return 50;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 25, ordinal = 1))
    private static int setTurtleDurabilityMultiplier(int constant) {
        return 15;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 33))
    private static int setDiamondDurabilityMultiplier(int constant) {
        return 20;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 7))
    private static int setGoldDurabilityMultiplier(int constant) {
        return 5;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 15, ordinal = 1))
    private static int setChainDurabilityMultiplier(int constant) {
        return 10;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 15, ordinal = 2))
    private static int setIronDurabilityMultiplier(int constant) {
        return 10;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 5, ordinal = 0))
    private static int setLeatherDurabilityMultiplier(int constant) {
        return 10;
    }
}
