package me.pajic.affogatotweaks.mixin.nightvision;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(PotionBrewing.class)
public abstract class PotionBrewingMixin {

    @ModifyArgs(
            method = "addVanillaMixes",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/alchemy/PotionBrewing$Builder;addMix(Lnet/minecraft/core/Holder;Lnet/minecraft/world/item/Item;Lnet/minecraft/core/Holder;)V",
                    ordinal = 3
            )
    )
    private static void changeNightVisionRecipeToInvisibilityRecipe(Args args) {
        args.set(2, Potions.INVISIBILITY);
    }

    @WrapWithCondition(
            method = "addVanillaMixes",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/alchemy/PotionBrewing$Builder;addMix(Lnet/minecraft/core/Holder;Lnet/minecraft/world/item/Item;Lnet/minecraft/core/Holder;)V",
                    ordinal = 4
            )
    )
    private static boolean disableLongNightVisionRecipe(PotionBrewing.Builder instance, Holder<Potion> input, Item reagent, Holder<Potion> result) {
        return false;
    }

    @WrapWithCondition(
            method = "addVanillaMixes",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/alchemy/PotionBrewing$Builder;addMix(Lnet/minecraft/core/Holder;Lnet/minecraft/world/item/Item;Lnet/minecraft/core/Holder;)V",
                    ordinal = 5
            )
    )
    private static boolean disableOriginalInvisibilityRecipe(PotionBrewing.Builder instance, Holder<Potion> input, Item reagent, Holder<Potion> result) {
        return false;
    }
}