package me.pajic.affogatotweaks.mixin;

import me.pajic.affogatotweaks.util.EnchantmentUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(TradeOffers.EnchantBookFactory.class)
public abstract class EnchantBookFactoryMixin {

    @Unique
    private List<Enchantment> modifiedList;

    @Final
    @Shadow
    private int experience;

    @ModifyVariable(method = "create", at = @At("STORE"))
    private List<Enchantment> modifyEnchantmentList(List<Enchantment> list) {
        modifiedList = EnchantmentUtil.removeEnchantmentsFromList(list);
        return modifiedList;
    }

    @Inject(method = "create", at = @At(value = "INVOKE", target = "Ljava/util/List;get(I)Ljava/lang/Object;", shift = At.Shift.BEFORE), cancellable = true)
    private void replaceTrade(Entity entity, Random random, CallbackInfoReturnable<TradeOffer> cir) {
        if (modifiedList.isEmpty()) {
            cir.setReturnValue(new TradeOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.BOOK, 1), 16, 10, 0.05F));
        }
    }

    @Redirect(method = "create", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;nextInt(Lnet/minecraft/util/math/random/Random;II)I"))
    private int setMaxEnchantmentLevel(Random random, int min, int max) {
        return 1;
    }

    @Inject(method = "create", at = @At("RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void setTradeUses(Entity entity, Random random, CallbackInfoReturnable<TradeOffer> cir, List list, Enchantment enchantment, int i, ItemStack itemStack, int j) {
        cir.setReturnValue(new TradeOffer(new ItemStack(Items.EMERALD, j), new ItemStack(Items.BOOK), itemStack, 1, this.experience, 0.2F));
    }
}
