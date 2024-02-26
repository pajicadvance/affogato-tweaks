package me.pajic.affogatotweaks.mixin;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {

    // Increases the amount of items a blaze rod can smelt from 12 to 16
    @ModifyConstant(method = "createFuelTimeMap", constant = @Constant(intValue = 2400))
    private static int setBlazeRodFuelTime(int constant) {
        return 3200;
    }
}
