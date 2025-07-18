package me.pajic.affogatotweaks.mixin.stats;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.EnumMap;

@Mixin(ArmorMaterials.class)
public class ArmorMaterialsMixin {

    @Inject(method = "method_48406", at = @At("HEAD"), cancellable = true)
    private static void setNetheriteArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.put(ArmorItem.Type.HELMET, 2);
        map.put(ArmorItem.Type.CHESTPLATE, 7);
        map.put(ArmorItem.Type.LEGGINGS, 5);
        map.put(ArmorItem.Type.BOOTS, 2);
        map.put(ArmorItem.Type.BODY, 7);
    }

    @Inject(method = "method_48409", at = @At("HEAD"), cancellable = true)
    private static void setGoldArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.put(ArmorItem.Type.HELMET, 1);
        map.put(ArmorItem.Type.CHESTPLATE, 3);
        map.put(ArmorItem.Type.LEGGINGS, 3);
        map.put(ArmorItem.Type.BOOTS, 1);
        map.put(ArmorItem.Type.BODY, 3);
    }

    @Inject(method = "method_48410", at = @At("HEAD"), cancellable = true)
    private static void setIronArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.put(ArmorItem.Type.HELMET, 2);
        map.put(ArmorItem.Type.CHESTPLATE, 5);
        map.put(ArmorItem.Type.LEGGINGS, 3);
        map.put(ArmorItem.Type.BOOTS, 2);
        map.put(ArmorItem.Type.BODY, 5);
    }

    @Inject(method = "method_48411", at = @At("HEAD"), cancellable = true)
    private static void setChainArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.put(ArmorItem.Type.HELMET, 2);
        map.put(ArmorItem.Type.CHESTPLATE, 5);
        map.put(ArmorItem.Type.LEGGINGS, 3);
        map.put(ArmorItem.Type.BOOTS, 2);
        map.put(ArmorItem.Type.BODY, 5);
    }

    @Inject(method = "method_48412", at = @At("HEAD"), cancellable = true)
    private static void setLeatherArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.put(ArmorItem.Type.HELMET, 1);
        map.put(ArmorItem.Type.CHESTPLATE, 2);
        map.put(ArmorItem.Type.LEGGINGS, 2);
        map.put(ArmorItem.Type.BOOTS, 1);
        map.put(ArmorItem.Type.BODY, 2);
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 2.0F))
    private static float setDiamondToughness(float constant) {
        return 0.0F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 3.0F))
    private static float setNetheriteToughness(float constant) {
        return 4.0F;
    }
}
