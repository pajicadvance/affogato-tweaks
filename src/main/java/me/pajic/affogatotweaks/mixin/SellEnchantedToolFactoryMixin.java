package me.pajic.affogatotweaks.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(TradeOffers.SellEnchantedToolFactory.class)
public class SellEnchantedToolFactoryMixin {

    @Shadow @Final private int experience;

    @Shadow @Final private float multiplier;

    // Sets the amount of uses of enchanted gear trades to 1
    @Inject(method = "create", at = @At("RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void setTradeUses(Entity entity, Random random, CallbackInfoReturnable<TradeOffer> cir, int i, ItemStack itemStack, int j, ItemStack itemStack2) {
        cir.setReturnValue(new TradeOffer(itemStack2, itemStack, 1, experience, multiplier));
    }
}
