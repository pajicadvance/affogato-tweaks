package me.pajic.affogatotweaks.mixin.potion;

import me.pajic.affogatotweaks.potion.ModPotions;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TippedArrowItem;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TippedArrowItem.class)
public abstract class TippedArrowItemMixin extends Item {
    public TippedArrowItemMixin(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return ModPotions.isLifeElixir(stack) ?
                super.getName(stack).copy().withStyle(ChatFormatting.LIGHT_PURPLE) :
                super.getName(stack);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return ModPotions.isLifeElixir(stack) || super.isFoil(stack);
    }
}
