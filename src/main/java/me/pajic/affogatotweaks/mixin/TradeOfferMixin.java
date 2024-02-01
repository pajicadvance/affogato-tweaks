package me.pajic.affogatotweaks.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TradeOffer.class)
public class TradeOfferMixin {

    @Final
    @Shadow
    private ItemStack sellItem;

    @Inject(method = "resetUses", at = @At("HEAD"), cancellable = true)
    private void skipEnchantedBookTradeRestock(CallbackInfo ci) {
        if (sellItem.isOf(Items.ENCHANTED_BOOK)) {
            ci.cancel();
        }
    }
}
