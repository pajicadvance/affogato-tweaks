package me.pajic.affogatotweaks.mixin;

import me.pajic.affogatotweaks.util.EnchantmentUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(EnchantRandomlyLootFunction.class)
public class EnchantRandomlyLootFunctionMixin {

    @ModifyVariable(method = "process", at = @At("STORE"))
    public List<Enchantment> modifyEnchantmentList(List<Enchantment> list) {
        return EnchantmentUtil.removeEnchantmentsFromList(list);
    }

    @Redirect(method = "addEnchantmentToStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;nextInt(Lnet/minecraft/util/math/random/Random;II)I"))
    private static int setMaxEnchantmentLevel(Random random, int min, int max) {
        return EnchantmentUtil.returnEnchantmentLevelFromPool(random, max);
    }
}
