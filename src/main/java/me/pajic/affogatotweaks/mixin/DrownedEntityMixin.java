package me.pajic.affogatotweaks.mixin;

import net.minecraft.entity.mob.DrownedEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(DrownedEntity.class)
public class DrownedEntityMixin {

    // Reduces drowned trident fire rate
    @ModifyConstant(method = "initCustomGoals", constant = @Constant(intValue = 40))
    private int setDrownedTridentFireRate(int constant) {
        return 80;
    }
}
