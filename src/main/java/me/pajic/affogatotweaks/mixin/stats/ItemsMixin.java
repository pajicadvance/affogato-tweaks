package me.pajic.affogatotweaks.mixin.stats;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogatotweaks.values.DurabilityValues;
import me.pajic.affogatotweaks.values.StatValues;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Items.class)
public abstract class ItemsMixin {

    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/BowItem;<init>(Lnet/minecraft/world/item/Item$Properties;)V"
            )
    )
    private static Item.Properties setBowDurability(Item.Properties settings) {
        return new Item.Properties().durability(DurabilityValues.BOW);
    }

    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ElytraItem;<init>(Lnet/minecraft/world/item/Item$Properties;)V"
            )
    )
    private static Item.Properties setElytraDurability(Item.Properties settings) {
        return new Item.Properties().durability(DurabilityValues.ELYTRA).rarity(Rarity.UNCOMMON);
    }

    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ShearsItem;<init>(Lnet/minecraft/world/item/Item$Properties;)V"
            )
    )
    private static Item.Properties setShearsDurability(Item.Properties settings) {
        return new Item.Properties().durability(DurabilityValues.SHEARS);
    }

    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ShieldItem;<init>(Lnet/minecraft/world/item/Item$Properties;)V"
            )
    )
    private static Item.Properties setShieldDurability(Item.Properties settings) {
        return new Item.Properties().durability(DurabilityValues.SHIELD);
    }

    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/CrossbowItem;<init>(Lnet/minecraft/world/item/Item$Properties;)V"
            )
    )
    private static Item.Properties setCrossbowDurability(Item.Properties settings) {
        return new Item.Properties().durability(DurabilityValues.CROSSBOW);
    }

    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/TridentItem;<init>(Lnet/minecraft/world/item/Item$Properties;)V"
            )
    )
    private static Item.Properties setTridentDurability(Item.Properties settings) {
        return new Item.Properties().durability(DurabilityValues.TRIDENT);
    }

    @Definition(id = "createAttributes", method = "Lnet/minecraft/world/item/AxeItem;createAttributes(Lnet/minecraft/world/item/Tier;FF)Lnet/minecraft/world/item/component/ItemAttributeModifiers;")
    @Definition(id = "WOOD", field = "Lnet/minecraft/world/item/Tiers;WOOD:Lnet/minecraft/world/item/Tiers;")
    @Expression("createAttributes(WOOD, ?, @(?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static float setWoodenAxeAttackSpeed(float original) {
        return StatValues.AXE_ATTACK_SPEED;
    }

    @Definition(id = "createAttributes", method = "Lnet/minecraft/world/item/AxeItem;createAttributes(Lnet/minecraft/world/item/Tier;FF)Lnet/minecraft/world/item/component/ItemAttributeModifiers;")
    @Definition(id = "STONE", field = "Lnet/minecraft/world/item/Tiers;STONE:Lnet/minecraft/world/item/Tiers;")
    @Expression("createAttributes(STONE, ?, @(?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static float setStoneAxeAttackSpeed(float original) {
        return StatValues.AXE_ATTACK_SPEED;
    }

    @Definition(id = "createAttributes", method = "Lnet/minecraft/world/item/AxeItem;createAttributes(Lnet/minecraft/world/item/Tier;FF)Lnet/minecraft/world/item/component/ItemAttributeModifiers;")
    @Definition(id = "IRON", field = "Lnet/minecraft/world/item/Tiers;IRON:Lnet/minecraft/world/item/Tiers;")
    @Expression("createAttributes(IRON, ?, @(?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static float setIronAxeAttackSpeed(float original) {
        return StatValues.AXE_ATTACK_SPEED;
    }

    @Definition(id = "createAttributes", method = "Lnet/minecraft/world/item/AxeItem;createAttributes(Lnet/minecraft/world/item/Tier;FF)Lnet/minecraft/world/item/component/ItemAttributeModifiers;")
    @Definition(id = "DIAMOND", field = "Lnet/minecraft/world/item/Tiers;DIAMOND:Lnet/minecraft/world/item/Tiers;")
    @Expression("createAttributes(DIAMOND, ?, @(?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static float setDiamondAxeAttackSpeed(float original) {
        return StatValues.AXE_ATTACK_SPEED;
    }

    @Definition(id = "createAttributes", method = "Lnet/minecraft/world/item/AxeItem;createAttributes(Lnet/minecraft/world/item/Tier;FF)Lnet/minecraft/world/item/component/ItemAttributeModifiers;")
    @Definition(id = "NETHERITE", field = "Lnet/minecraft/world/item/Tiers;NETHERITE:Lnet/minecraft/world/item/Tiers;")
    @Expression("createAttributes(NETHERITE, ?, @(?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static float setNetheriteAxeAttackSpeed(float original) {
        return StatValues.AXE_ATTACK_SPEED;
    }

    @Definition(id = "createAttributes", method = "Lnet/minecraft/world/item/HoeItem;createAttributes(Lnet/minecraft/world/item/Tier;FF)Lnet/minecraft/world/item/component/ItemAttributeModifiers;")
    @Definition(id = "WOOD", field = "Lnet/minecraft/world/item/Tiers;WOOD:Lnet/minecraft/world/item/Tiers;")
    @Expression("createAttributes(WOOD, ?, @(?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static float setWoodenHoeAttackSpeed(float original) {
        return StatValues.HOE_ATTACK_SPEED;
    }

    @Definition(id = "createAttributes", method = "Lnet/minecraft/world/item/HoeItem;createAttributes(Lnet/minecraft/world/item/Tier;FF)Lnet/minecraft/world/item/component/ItemAttributeModifiers;")
    @Definition(id = "IRON", field = "Lnet/minecraft/world/item/Tiers;IRON:Lnet/minecraft/world/item/Tiers;")
    @Expression("createAttributes(IRON, ?, @(?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static float setIronHoeAttackSpeed(float original) {
        return StatValues.HOE_ATTACK_SPEED;
    }

    @Definition(id = "createAttributes", method = "Lnet/minecraft/world/item/HoeItem;createAttributes(Lnet/minecraft/world/item/Tier;FF)Lnet/minecraft/world/item/component/ItemAttributeModifiers;")
    @Definition(id = "DIAMOND", field = "Lnet/minecraft/world/item/Tiers;DIAMOND:Lnet/minecraft/world/item/Tiers;")
    @Expression("createAttributes(DIAMOND, ?, ?)")
    @ModifyArgs(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static void setDiamondHoeStats(Args args) {
        args.set(2, StatValues.HOE_ATTACK_SPEED);
    }

    @Definition(id = "createAttributes", method = "Lnet/minecraft/world/item/HoeItem;createAttributes(Lnet/minecraft/world/item/Tier;FF)Lnet/minecraft/world/item/component/ItemAttributeModifiers;")
    @Definition(id = "NETHERITE", field = "Lnet/minecraft/world/item/Tiers;NETHERITE:Lnet/minecraft/world/item/Tiers;")
    @Expression("createAttributes(NETHERITE, ?, ?)")
    @ModifyArgs(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static void setNetheriteHoeStats(Args args) {
        args.set(2, StatValues.HOE_ATTACK_SPEED);
    }

    @Definition(id = "createAttributes", method = "Lnet/minecraft/world/item/SwordItem;createAttributes(Lnet/minecraft/world/item/Tier;IF)Lnet/minecraft/world/item/component/ItemAttributeModifiers;")
    @Definition(id = "GOLD", field = "Lnet/minecraft/world/item/Tiers;GOLD:Lnet/minecraft/world/item/Tiers;")
    @Expression("createAttributes(GOLD, ?, @(?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static float setGoldenSwordAttackSpeed(float original) {
        return StatValues.GOLDEN_SWORD_ATTACK_SPEED;
    }

    @Definition(id = "createAttributes", method = "Lnet/minecraft/world/item/PickaxeItem;createAttributes(Lnet/minecraft/world/item/Tier;FF)Lnet/minecraft/world/item/component/ItemAttributeModifiers;")
    @Definition(id = "GOLD", field = "Lnet/minecraft/world/item/Tiers;GOLD:Lnet/minecraft/world/item/Tiers;")
    @Expression("createAttributes(GOLD, ?, @(?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static float setGoldenPickaxeAttackSpeed(float original) {
        return StatValues.GOLDEN_PICKAXE_ATTACK_SPEED;
    }

    @Definition(id = "createAttributes", method = "Lnet/minecraft/world/item/ShovelItem;createAttributes(Lnet/minecraft/world/item/Tier;FF)Lnet/minecraft/world/item/component/ItemAttributeModifiers;")
    @Definition(id = "GOLD", field = "Lnet/minecraft/world/item/Tiers;GOLD:Lnet/minecraft/world/item/Tiers;")
    @Expression("createAttributes(GOLD, ?, @(?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static float setGoldenShovelAttackSpeed(float original) {
        return StatValues.GOLDEN_SHOVEL_ATTACK_SPEED;
    }

    @Definition(id = "createAttributes", method = "Lnet/minecraft/world/item/HoeItem;createAttributes(Lnet/minecraft/world/item/Tier;FF)Lnet/minecraft/world/item/component/ItemAttributeModifiers;")
    @Definition(id = "GOLD", field = "Lnet/minecraft/world/item/Tiers;GOLD:Lnet/minecraft/world/item/Tiers;")
    @Expression("createAttributes(GOLD, ?, @(?))")
    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static float setGoldenHoeAttackSpeed(float original) {
        return StatValues.GOLDEN_HOE_ATTACK_SPEED;
    }
}