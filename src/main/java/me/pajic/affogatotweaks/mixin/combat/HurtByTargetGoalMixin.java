package me.pajic.affogatotweaks.mixin.combat;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogatotweaks.values.MobValues;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HurtByTargetGoal.class)
public abstract class HurtByTargetGoalMixin extends TargetGoal {
    public HurtByTargetGoalMixin(Mob mob, boolean mustSee) {
        super(mob, mustSee);
    }

    @ModifyExpressionValue(
            method = "alertOthers",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ai/goal/target/HurtByTargetGoal;getFollowDistance()D"
            )
    )
    private double setAlertRange(double original) {
        return mob instanceof Zombie ? MobValues.ZOMBIE_ALERT_OTHER_RANGE : original;
    }
}
