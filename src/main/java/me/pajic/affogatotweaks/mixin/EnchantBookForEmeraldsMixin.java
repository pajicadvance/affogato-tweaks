package me.pajic.affogatotweaks.mixin;

import me.pajic.affogatotweaks.util.EnchantmentUtil;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.trading.MerchantOffer;
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

@Mixin(VillagerTrades.EnchantBookForEmeralds.class)
public abstract class EnchantBookForEmeraldsMixin {

    @Unique
    private List<Enchantment> modifiedList;

    @Final
    @Shadow
    private int villagerXp;

    // Removes blacklisted enchantments from the list of possible enchantments for enchanted book trades
    @ModifyVariable(method = "getOffer", at = @At("STORE"))
    private List<Enchantment> modifyEnchantmentList(List<Enchantment> list) {
        modifiedList = EnchantmentUtil.removeEnchantmentsFromList(list);
        return modifiedList;
    }

    // If the list ends up empty, the enchanted book trade is replaced with a regular book trade
    @Inject(method = "getOffer", at = @At(value = "INVOKE", target = "Ljava/util/List;get(I)Ljava/lang/Object;", shift = At.Shift.BEFORE), cancellable = true)
    private void replaceTrade(Entity entity, RandomSource random, CallbackInfoReturnable<MerchantOffer> cir) {
        if (modifiedList.isEmpty()) {
            cir.setReturnValue(new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.BOOK, 1), 16, 10, 0.05F));
        }
    }

    // Limits the maximum enchantment level of enchanted book trades to 1
    @Redirect(method = "getOffer", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;nextInt(Lnet/minecraft/util/RandomSource;II)I"))
    private int setMaxEnchantmentLevel(RandomSource random, int min, int max) {
        return 1;
    }

    // Sets the amount of uses of enchanted book trades to 1
    @Inject(method = "getOffer", at = @At("RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void setTradeUses(Entity entity, RandomSource random, CallbackInfoReturnable<MerchantOffer> cir, List list, Enchantment enchantment, int i, ItemStack itemStack, int j) {
        cir.setReturnValue(new MerchantOffer(new ItemStack(Items.EMERALD, j), new ItemStack(Items.BOOK), itemStack, 1, this.villagerXp, 0.2F));
    }
}
