package me.pajic.affogato_core.mixin.hunger;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.pajic.affogato_core.Main;
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
    private boolean throwWeaponExhaustion(ItemStack stack, Level level, LivingEntity livingEntity, int timeCharged, Operation<Void> original) {
        if (livingEntity instanceof Player player && !level.isClientSide() && stack.is(ItemTags.AXES)) {
            player.causeFoodExhaustion(Main.CONFIG.exhaustionValues.throwWeapon.get());
        }
        original.call(stack, level, livingEntity, timeCharged);
        return false;
    }
}
