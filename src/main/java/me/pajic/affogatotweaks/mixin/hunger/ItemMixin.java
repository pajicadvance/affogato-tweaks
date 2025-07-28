package me.pajic.affogatotweaks.mixin.hunger;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.pajic.affogatotweaks.values.ExhaustionValues;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.class)
public class ItemMixin {

    @WrapMethod(method = "releaseUsing")
    private void throwWeaponExhaustion(ItemStack stack, Level level, LivingEntity livingEntity, int timeCharged, Operation<Void> original) {
        if (livingEntity instanceof Player player && !level.isClientSide && stack.is(ItemTags.AXES)) {
            player.causeFoodExhaustion(ExhaustionValues.THROW_WEAPON);
        }
        original.call(stack, level, livingEntity, timeCharged);
    }
}
