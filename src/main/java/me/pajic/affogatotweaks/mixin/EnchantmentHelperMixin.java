package me.pajic.affogatotweaks.mixin;

import me.pajic.affogatotweaks.util.EnchantmentUtil;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    // Prevents addition of blacklisted enchantments to the list of possible enchantments when enchanting an item
    @Redirect(method = "getAvailableEnchantmentResults", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
    private static <E> boolean redirectListAdd(List<EnchantmentInstance> instance, E e) {
        EnchantmentInstance entry = (EnchantmentInstance) e;
        return EnchantmentUtil.preventEnchantmentAdditionToList(instance, entry);
    }
}
