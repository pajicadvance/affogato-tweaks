package me.pajic.affogatotweaks.mixin.hunger;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.pajic.affogatotweaks.values.ExhaustionValues;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TridentItem.class)
public class TridentItemMixin {

    @WrapMethod(method = "releaseUsing")
    private void throwWeaponExhaustion(ItemStack stack, Level level, LivingEntity livingEntity, int timeCharged, Operation<Void> original) {
        if (livingEntity instanceof Player player && !level.isClientSide && stack.is(Items.TRIDENT)) {
            player.causeFoodExhaustion(ExhaustionValues.throwWeapon());
        }
        original.call(stack, level, livingEntity, timeCharged);
    }
}
