package me.pajic.affogatotweaks.mixin.gameplay;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Mob.class)
public class MobMixin {

    @WrapMethod(method = "canHoldItem")
    private boolean allowAnimalFoodPickup(ItemStack stack, Operation<Boolean> original) {
        return (Mob) (Object) this instanceof Animal animal && !animal.isBaby() && animal.getAge() == 0 && animal.canFallInLove() ?
                animal.isFood(stack) : original.call(stack);
    }

    @WrapWithCondition(
            method = "aiStep",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Mob;pickUpItem(Lnet/minecraft/world/entity/item/ItemEntity;)V"
            )
    )
    private boolean animalPickupFood(Mob instance, ItemEntity itemEntity) {
        if (instance instanceof Animal animal) {
            if (!animal.isInLove() && animal.isFood(itemEntity.getItem())) {
                ItemStack itemStack = itemEntity.getItem();
                animal.onItemPickup(itemEntity);
                animal.take(itemEntity, 1);
                itemStack.shrink(1);
                if (itemStack.isEmpty()) itemEntity.discard();
                animal.setInLove(itemEntity.getOwner() instanceof Player player ? player : null);
            }
            return false;
        }
        return true;
    }
}
