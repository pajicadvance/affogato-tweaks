package me.pajic.affogatotweaks.mixin;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.EnumMap;

@Mixin(ArmorMaterials.class)
public class ArmorMaterialsMixin {

    @Inject(method = "method_48406", at = @At("HEAD"), cancellable = true)
    private static void setNetheriteArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.put(ArmorItem.Type.HELMET, 3);
        map.put(ArmorItem.Type.CHESTPLATE, 5);
        map.put(ArmorItem.Type.LEGGINGS, 4);
        map.put(ArmorItem.Type.BOOTS, 3);
    }

    @Inject(method = "method_48409", at = @At("HEAD"), cancellable = true)
    private static void setGoldArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.put(ArmorItem.Type.HELMET, 1);
        map.put(ArmorItem.Type.CHESTPLATE, 4);
        map.put(ArmorItem.Type.LEGGINGS, 3);
        map.put(ArmorItem.Type.BOOTS, 1);
    }

    @Inject(method = "method_48410", at = @At("HEAD"), cancellable = true)
    private static void setIronArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.put(ArmorItem.Type.HELMET, 2);
        map.put(ArmorItem.Type.CHESTPLATE, 5);
        map.put(ArmorItem.Type.LEGGINGS, 4);
        map.put(ArmorItem.Type.BOOTS, 1);
    }

    @Inject(method = "method_48412", at = @At("HEAD"), cancellable = true)
    private static void setLeatherArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.put(ArmorItem.Type.HELMET, 1);
        map.put(ArmorItem.Type.CHESTPLATE, 2);
        map.put(ArmorItem.Type.LEGGINGS, 2);
        map.put(ArmorItem.Type.BOOTS, 1);
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 37))
    private static int setNetheriteDurabilityMultiplier(int constant) {
        return 40;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 25))
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

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 15))
    private static int setIronAndChainDurabilityMultiplier(int constant) {
        return 10;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 5))
    private static int setLeatherDurabilityMultiplier(int constant) {
        return 3;
    }
}
