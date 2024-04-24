package me.pajic.affogatotweaks.mixin;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(VillagerTrades.EnchantedItemForEmeralds.class)
public class EnchantedItemForEmeraldsMixin {

    @Shadow @Final private int villagerXp;

    @Shadow @Final private float priceMultiplier;

    // Sets the amount of uses of enchanted gear trades to 1
    @Inject(method = "getOffer", at = @At("RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void setTradeUses(Entity entity, RandomSource random, CallbackInfoReturnable<MerchantOffer> cir, int i, ItemStack itemStack, int j, ItemStack itemStack2) {
        cir.setReturnValue(new MerchantOffer(itemStack2, itemStack, 1, villagerXp, priceMultiplier));
    }
}
