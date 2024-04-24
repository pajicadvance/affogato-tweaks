package me.pajic.affogatotweaks.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Items.class)
public abstract class ItemsMixin {

    // Changes the durabilities of certain items
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BoatItem;<init>(ZLnet/minecraft/world/entity/vehicle/Boat$Type;Lnet/minecraft/world/item/Item$Properties;)V"))
    private static Item.Properties setBowDurability(Item.Properties settings) {
        return new Item.Properties().durability(256);
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ElytraItem;<init>(Lnet/minecraft/world/item/Item$Properties;)V"))
    private static Item.Properties setElytraDurability(Item.Properties settings) {
        return new Item.Properties().durability(640).rarity(Rarity.UNCOMMON);
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ShearsItem;<init>(Lnet/minecraft/world/item/Item$Properties;)V"))
    private static Item.Properties setShearsDurability(Item.Properties settings) {
        return new Item.Properties().durability(256);
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ShieldItem;<init>(Lnet/minecraft/world/item/Item$Properties;)V"))
    private static Item.Properties setShieldDurability(Item.Properties settings) {
        return new Item.Properties().durability(384);
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/CrossbowItem;<init>(Lnet/minecraft/world/item/Item$Properties;)V"))
    private static Item.Properties setCrossbowDurability(Item.Properties settings) {
        return new Item.Properties().durability(320);
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/TridentItem;<init>(Lnet/minecraft/world/item/Item$Properties;)V"))
    private static Item.Properties setTridentDurability(Item.Properties settings) {
        return new Item.Properties().durability(384);
    }

    // Changes the attack speed of certain items
    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = -2.4F, ordinal = 2))
    private static float setGoldenSwordAttackSpeed(float constant) {
        return -1.6F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = -3.2F))
    private static float setWoodenAndStoneAxeAttackSpeed(float constant) {
        return -3.5F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = -3.1F))
    private static float setIronAxeAttackSpeed(float constant) {
        return -3.5F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = -2.8F, ordinal = 2))
    private static float setGoldenPickaxeAttackSpeed(float constant) {
        return -2.2F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = -3.0F, ordinal = 8))
    private static float setDiamondAxeAttackSpeed(float constant) {
        return -3.5F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = -3.0F, ordinal = 10))
    private static float setNetheriteAxeAttackSpeed(float constant) {
        return -3.5F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = -3.0F, ordinal = 3))
    private static float setGoldenShovelAttackSpeed(float constant) {
        return -2.5F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = -3.0F, ordinal = 1))
    private static float setWoodenHoeAttackSpeed(float constant) {
        return -2.0F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = -1.0F))
    private static float setIronHoeAttackSpeed(float constant) {
        return -2.0F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 0.0F))
    private static float setDiamondAndNetheriteHoeAttackSpeed(float constant) {
        return -2.0F;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = -3.0F, ordinal = 5))
    private static float setGoldenHoeAttackSpeed(float constant) {
        return 0.0F;
    }
}
