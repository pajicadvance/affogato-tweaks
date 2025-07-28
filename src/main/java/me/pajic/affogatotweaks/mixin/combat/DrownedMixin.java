package me.pajic.affogatotweaks.mixin.combat;

import me.pajic.affogatotweaks.values.MobValues;
import net.minecraft.world.entity.monster.Drowned;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Drowned.class)
public class DrownedMixin {

    @ModifyConstant(method = "addBehaviourGoals", constant = @Constant(intValue = 40))
    private int setDrownedTridentFireRate(int constant) {
        return MobValues.DROWNED_TRIDENT_THROW_DELAY;
    }
}