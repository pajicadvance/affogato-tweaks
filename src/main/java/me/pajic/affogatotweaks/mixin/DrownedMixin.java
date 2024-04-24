package me.pajic.affogatotweaks.mixin;

import net.minecraft.world.entity.monster.Drowned;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Drowned.class)
public class DrownedMixin {

    // Reduces drowned trident fire rate
    @ModifyConstant(method = "addBehaviourGoals", constant = @Constant(intValue = 40))
    private int setDrownedTridentFireRate(int constant) {
        return 80;
    }
}
