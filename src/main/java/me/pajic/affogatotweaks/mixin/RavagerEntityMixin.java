package me.pajic.affogatotweaks.mixin;

import net.minecraft.entity.mob.RavagerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(RavagerEntity.class)
public class RavagerEntityMixin {

    // Decreases ravager roar knockback intensity
    @ModifyConstant(method = "knockBack", constant = @Constant(doubleValue = 0.001))
    private double modifyRoarKnockbackMaxIntensity(double constant) {
        return 0.1;
    }
}
