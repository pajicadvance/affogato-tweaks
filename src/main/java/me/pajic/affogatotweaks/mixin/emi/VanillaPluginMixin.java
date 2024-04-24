package me.pajic.affogatotweaks.mixin.emi;

import dev.emi.emi.VanillaPlugin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(VanillaPlugin.class)
public class VanillaPluginMixin {

    // Modifies the tipped arrow recipes in EMI to show regular potions instead of lingering potions
    @ModifyArg(method = "lambda$register$12", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/alchemy/PotionUtils;setPotion(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/alchemy/Potion;)Lnet/minecraft/world/item/ItemStack;", ordinal = 0))
    private static ItemStack setEmiTippedArrowRecipe(ItemStack stack) {
        return new ItemStack(Items.POTION);
    }
}
