package me.pajic.affogatotweaks.mixin;

import net.minecraft.world.entity.monster.AbstractSkeleton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AbstractSkeleton.class)
public class AbstractSkeletonMixin {

    // Reduce skeleton bow fire rate on Hard difficulty
    @ModifyConstant(method = "reassessWeaponGoal", constant = @Constant(intValue = 20))
    private int modifyFireRate(int constant) {
        return 30;
    }
}
