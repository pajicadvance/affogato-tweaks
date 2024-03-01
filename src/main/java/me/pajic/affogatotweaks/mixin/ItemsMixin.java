package me.pajic.affogatotweaks.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Items.class)
public abstract class ItemsMixin {

    // Changes the durabilities of certain items
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BowItem;<init>(Lnet/minecraft/item/Item$Settings;)V"))
    private static Item.Settings setBowDurability(Item.Settings settings) {
        return new Item.Settings().maxDamage(256);
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ElytraItem;<init>(Lnet/minecraft/item/Item$Settings;)V"))
    private static Item.Settings setElytraDurability(Item.Settings settings) {
        return new Item.Settings().maxDamage(640).rarity(Rarity.UNCOMMON);
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ShearsItem;<init>(Lnet/minecraft/item/Item$Settings;)V"))
    private static Item.Settings setShearsDurability(Item.Settings settings) {
        return new Item.Settings().maxDamage(256);
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ShieldItem;<init>(Lnet/minecraft/item/Item$Settings;)V"))
    private static Item.Settings setShieldDurability(Item.Settings settings) {
        return new Item.Settings().maxDamage(256);
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/CrossbowItem;<init>(Lnet/minecraft/item/Item$Settings;)V"))
    private static Item.Settings setCrossbowDurability(Item.Settings settings) {
        return new Item.Settings().maxDamage(320);
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/TridentItem;<init>(Lnet/minecraft/item/Item$Settings;)V"))
    private static Item.Settings setTridentDurability(Item.Settings settings) {
        return new Item.Settings().maxDamage(384);
    }

    // Changes the attack speed of axes
    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = -3.2F))
    private static float setWoodenAndStoneAxeAttackSpeed(float constant) {
        return -4.8F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = -3.1F))
    private static float setIronAxeAttackSpeed(float constant) {
        return -4.8F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = -3.0F, ordinal = 4))
    private static float setGoldenAxeAttackSpeed(float constant) {
        return -4.8F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = -3.0F, ordinal = 8))
    private static float setDiamondAxeAttackSpeed(float constant) {
        return -4.8F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = -3.0F, ordinal = 10))
    private static float setNetheriteAxeAttackSpeed(float constant) {
        return -4.8F;
    }
}
