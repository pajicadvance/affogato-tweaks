package me.pajic.affogatotweaks.mixin;

import net.minecraft.world.entity.monster.Ravager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Ravager.class)
public class RavagerMixin {

    // Decreases ravager roar knockback intensity
    @ModifyConstant(method = "strongKnockback", constant = @Constant(doubleValue = 0.001))
    private double modifyRoarKnockbackMaxIntensity(double constant) {
        return 0.1;
    }
}
