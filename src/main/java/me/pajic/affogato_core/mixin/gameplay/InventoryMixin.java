package me.pajic.affogato_core.mixin.gameplay;

import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogato_core.Main;
import net.minecraft.core.NonNullList;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Inventory.class)
public class InventoryMixin {

    @Shadow @Final public Player player;
    @Shadow @Final private NonNullList<ItemStack> items;

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;inventoryTick(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/EquipmentSlot;)V"
            )
    )
    private void applySlownessIfAnvilInInventory(CallbackInfo ci, @Local(name = "i") int i) {
        if (Main.CONFIG.features.holdingAnvilAppliesSlowness.get()) {
            GameType gameType = player.gameMode();
            if (gameType != null && gameType.isSurvival() && items.get(i).is(Items.ANVIL)) {
                player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20, 4));
            }
        }
    }
}
