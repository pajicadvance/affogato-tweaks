package me.pajic.affogatotweaks.mixin.emi;

import dev.emi.emi.VanillaPlugin;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(VanillaPlugin.class)
public class VanillaPluginMixin {

    @ModifyArg(method = "lambda$register$12", at = @At(value = "INVOKE", target = "Lnet/minecraft/potion/PotionUtil;setPotion(Lnet/minecraft/item/ItemStack;Lnet/minecraft/potion/Potion;)Lnet/minecraft/item/ItemStack;", ordinal = 0))
    private static ItemStack setEmiTippedArrowRecipe(ItemStack stack) {
        return new ItemStack(Items.POTION);
    }
}
