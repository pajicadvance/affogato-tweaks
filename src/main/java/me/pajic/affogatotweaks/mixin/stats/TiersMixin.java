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
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(Tiers.class)
public class TiersMixin {
    @Shadow @Mutable @Final private float damage;
    @Shadow @Mutable @Final private int uses;
    @Shadow @Mutable @Final private float speed;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void modifyStats(
            String string,
            int ordinal,
            TagKey<Block> incorrectBlockForDrops,
            int _uses,
            float spd,
            float dmg,
            int enchantmentValue,
            Supplier<Ingredient> repairIngredient,
            CallbackInfo ci
    ) {
        switch (ordinal) {
            case 0:
                uses = DurabilityValues.WOODEN_TOOL;
                speed = StatValues.WOOD_MINING_SPEED;
                break;
            case 1:
                uses = DurabilityValues.STONE_TOOL;
                speed = StatValues.STONE_MINING_SPEED;
                break;
            case 2:
                uses = DurabilityValues.IRON_TOOL;
                speed = StatValues.IRON_MINING_SPEED;
                break;
            case 3:
                uses = DurabilityValues.DIAMOND_TOOL;
                speed = StatValues.DIAMOND_MINING_SPEED;
                damage = StatValues.DIAMOND_DAMAGE;
                break;
            case 4:
                uses = DurabilityValues.GOLDEN_TOOL;
                speed = StatValues.GOLD_MINING_SPEED;
                break;
            case 5:
                uses = DurabilityValues.NETHERITE_TOOL;
                damage = StatValues.NETHERITE_DAMAGE;
                break;
        }
    }
}