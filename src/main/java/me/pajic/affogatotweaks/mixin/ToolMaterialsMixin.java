package me.pajic.affogatotweaks.mixin;

import net.minecraft.item.ToolMaterials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(ToolMaterials.class)
public class ToolMaterialsMixin {

    @Mutable @Shadow @Final private float attackDamage;

    // Modifies the durability of tools
    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 59))
    private static int setWoodDurability(int constant) {
        return 48;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 131))
    private static int setStoneDurability(int constant) {
        return 96;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 250))
    private static int setIronDurability(int constant) {
        return 320;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 32))
    private static int setGoldDurability(int constant) {
        return 96;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 1561))
    private static int setDiamondDurability(int constant) {
        return 1024;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 2031))
    private static int setNetheriteDurability(int constant) {
        return 2560;
    }

    // Modifies the attack damage of certain tools
    @Inject(method = "<init>", at = @At("RETURN"))
    private void modifyAttackDamage(String name, int ordinal, int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier repairIngredient, CallbackInfo ci) {
        // diamond
        if (ordinal == 3) {
            this.attackDamage = 4.0f;
        }
        // netherite
        if (ordinal == 5) {
            this.attackDamage = 3.0f;
        }
    }

    // Modifies the mining speed of certain tools
    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 8.0F))
    private static float setDiamondMiningSpeed(float constant) {
        return 11.0F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 12.0F))
    private static float setGoldMiningSpeed(float constant) {
        return 20.0F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 2.0F, ordinal = 0))
    private static float setWoodMiningSpeed(float constant) {
        return 3.0F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 4.0F, ordinal = 0))
    private static float setStoneMiningSpeed(float constant) {
        return 5.0F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 6.0F))
    private static float setIronMiningSpeed(float constant) {
        return 7.0F;
    }

}
