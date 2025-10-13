package me.pajic.affogatotweaks.mixin.oxidation;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import me.pajic.affogatotweaks.oxidation.OxidationData;
import me.pajic.affogatotweaks.oxidation.OxidationUtil;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements DataComponentHolder {

    @ModifyReturnValue(
            method = "getHoverName",
            at = @At("RETURN")
    )
    private Component addOxidationPrefix(Component original) {
        Component name = switch (OxidationUtil.getItemOxidation((ItemStack) (Object) this)) {
            case 1 -> Component.translatable("text.affogatotweaks.exposed", original);
            case 2 -> Component.translatable("text.affogatotweaks.weathered", original);
            case 3 -> Component.translatable("text.affogatotweaks.oxidized", original);
            default -> original;
        };
        return has(OxidationData.WAXED) ? Component.translatable("text.affogatotweaks.waxed", name) : name;
    }
}
