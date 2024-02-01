package me.pajic.affogatotweaks.mixin;

import net.minecraft.item.ToolMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ToolMaterials.class)
public class ToolMaterialsMixin {

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
        return 192;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 1561))
    private static int setDiamondDurability(int constant) {
        return 1024;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 2031))
    private static int setNetheriteDurability(int constant) {
        return 2560;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 8.0F))
    private static float setDiamondMiningSpeed(float constant) {
        return 10.0F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 12.0F))
    private static float setGoldMiningSpeed(float constant) {
        return 16.0F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 9.0F))
    private static float setNetheriteMiningSpeed(float constant) {
        return 8.0F;
    }
}
