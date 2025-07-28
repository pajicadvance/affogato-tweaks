package me.pajic.affogatotweaks.mixin.stats;

import me.pajic.affogatotweaks.values.ArmorValues;
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
        map.putAll(ArmorValues.NETHERITE_ARMOR);
    }

    @Inject(method = "method_48409", at = @At("HEAD"), cancellable = true)
    private static void setGoldArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.putAll(ArmorValues.GOLD_ARMOR);
    }

    @Inject(method = "method_48410", at = @At("HEAD"), cancellable = true)
    private static void setIronArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.putAll(ArmorValues.IRON_ARMOR);
    }

    @Inject(method = "method_48411", at = @At("HEAD"), cancellable = true)
    private static void setChainArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.putAll(ArmorValues.IRON_ARMOR);
    }

    @Inject(method = "method_48412", at = @At("HEAD"), cancellable = true)
    private static void setLeatherArmorValues(EnumMap<ArmorItem.Type, Integer> map, CallbackInfo ci) {
        ci.cancel();
        map.putAll(ArmorValues.LEATHER_ARMOR);
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 2.0F))
    private static float setDiamondToughness(float constant) {
        return ArmorValues.DIAMOND_TOUGHNESS;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 3.0F))
    private static float setNetheriteToughness(float constant) {
        return ArmorValues.NETHERITE_TOUGHNESS;
    }
}
