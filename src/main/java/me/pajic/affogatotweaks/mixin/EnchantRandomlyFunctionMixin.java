package me.pajic.affogatotweaks.mixin;

import me.pajic.affogatotweaks.util.EnchantmentUtil;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(EnchantRandomlyFunction.class)
public class EnchantRandomlyFunctionMixin {

    // Removes blacklisted enchantments from the list of possible enchantments
    @ModifyVariable(method = "run", at = @At("STORE"))
    public List<Enchantment> modifyEnchantmentList(List<Enchantment> list) {
        return EnchantmentUtil.removeEnchantmentsFromList(list);
    }

    // Limits the maximum enchantment level to 1
    @Redirect(method = "enchantItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;nextInt(Lnet/minecraft/util/RandomSource;II)I"))
    private static int setMaxEnchantmentLevel(RandomSource random, int min, int max) {
        return EnchantmentUtil.returnEnchantmentLevelFromPool(random, max);
    }
}
