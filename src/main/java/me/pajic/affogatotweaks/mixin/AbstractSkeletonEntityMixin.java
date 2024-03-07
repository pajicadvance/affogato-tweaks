package me.pajic.affogatotweaks.mixin;

import net.minecraft.entity.mob.AbstractSkeletonEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AbstractSkeletonEntity.class)
public class AbstractSkeletonEntityMixin {

    // Reduce skeleton bow fire rate on Hard difficulty
    @ModifyConstant(method = "updateAttackType", constant = @Constant(intValue = 40))
    private int modifyFireRate(int constant) {
        return 30;
    }
}
