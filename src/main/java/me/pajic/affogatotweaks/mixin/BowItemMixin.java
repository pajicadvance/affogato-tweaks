package me.pajic.affogatotweaks.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowItem.class)
public class BowItemMixin {

    // Plays the bow drawing sound when the player starts drawing the bow
    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setCurrentHand(Lnet/minecraft/util/Hand;)V"))
    private void playBowDrawingSound(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (!user.getProjectileType(user.getStackInHand(hand)).isEmpty()) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_CROSSBOW_QUICK_CHARGE_1, SoundCategory.PLAYERS, 5.0F, 1.0F);
        }
    }
}
