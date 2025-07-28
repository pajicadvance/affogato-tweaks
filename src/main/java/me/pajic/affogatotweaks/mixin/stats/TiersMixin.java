package me.pajic.affogatotweaks.mixin.stats;

import me.pajic.affogatotweaks.values.DurabilityValues;
import me.pajic.affogatotweaks.values.StatValues;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(Tiers.class)
public class TiersMixin {

    @Shadow @Mutable @Final private float damage;

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 59))
    private static int setWoodDurability(int constant) {
        return DurabilityValues.WOODEN_TOOL;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 131))
    private static int setStoneDurability(int constant) {
        return DurabilityValues.STONE_TOOL;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 250))
    private static int setIronDurability(int constant) {
        return DurabilityValues.IRON_TOOL;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 32))
    private static int setGoldDurability(int constant) {
        return DurabilityValues.GOLDEN_TOOL;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 1561))
    private static int setDiamondDurability(int constant) {
        return DurabilityValues.DIAMOND_TOOL;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 2031))
    private static int setNetheriteDurability(int constant) {
        return DurabilityValues.NETHERITE_TOOL;
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void modifyAttackDamage(String string, int ordinal, TagKey<Block> incorrectBlockForDrops, int _uses, float speed, float dmg, int enchantmentValue, Supplier<Ingredient> repairIngredient, CallbackInfo ci) {
        if (ordinal == 3) damage = StatValues.DIAMOND_DAMAGE;
        if (ordinal == 5) damage = StatValues.NETHERITE_DAMAGE;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 8.0F))
    private static float setDiamondMiningSpeed(float constant) {
        return StatValues.DIAMOND_MINING_SPEED;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 12.0F))
    private static float setGoldMiningSpeed(float constant) {
        return StatValues.GOLD_MINING_SPEED;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 2.0F, ordinal = 0))
    private static float setWoodMiningSpeed(float constant) {
        return StatValues.WOOD_MINING_SPEED;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 4.0F, ordinal = 0))
    private static float setStoneMiningSpeed(float constant) {
        return StatValues.STONE_MINING_SPEED;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 6.0F))
    private static float setIronMiningSpeed(float constant) {
        return StatValues.IRON_MINING_SPEED;
    }
}