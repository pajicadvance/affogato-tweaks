package me.pajic.affogato_core.mixin.stats;

import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.util.ToolMaterialId;
import me.pajic.affogato_core.util.ToolStatReplacement;
import me.pajic.affogato_core.util.ToolType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.List;

@Mixin(Item.Properties.class)
public class ItemPropertiesMixin {

    @ModifyArgs(
            method = "pickaxe",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/Item$Properties;tool(Lnet/minecraft/world/item/ToolMaterial;Lnet/minecraft/tags/TagKey;FFF)Lnet/minecraft/world/item/Item$Properties;"
            )
    )
    private void modifyPickaxeStats(Args args, @Local(argsOnly = true) ToolMaterial material) {
        List<ToolStatReplacement> replacements = Main.CONFIG.toolStats.stream().filter(tsr ->
                ToolMaterialId.equal(tsr.material.get(), material) && tsr.type.get() == ToolType.PICKAXE
        ).toList();
        if (!replacements.isEmpty()) {
            Main.debugLog("Replacing stats for pickaxe {}", material);
            ToolStatReplacement replacement = replacements.getFirst();
            float dmg = replacement.attackDamage.get();
            float spd = replacement.attackSpeed.get();
            if (dmg != 99) args.set(2, dmg);
            if (spd != 99) args.set(3, spd);
        }
    }

    @ModifyArgs(
            method = "axe",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/Item$Properties;tool(Lnet/minecraft/world/item/ToolMaterial;Lnet/minecraft/tags/TagKey;FFF)Lnet/minecraft/world/item/Item$Properties;"
            )
    )
    private void modifyAxeStats(Args args, @Local(argsOnly = true) ToolMaterial material) {
        List<ToolStatReplacement> replacements = Main.CONFIG.toolStats.stream().filter(tsr ->
                ToolMaterialId.equal(tsr.material.get(), material) && tsr.type.get() == ToolType.AXE
        ).toList();
        if (!replacements.isEmpty()) {
            Main.debugLog("Replacing stats for axe {}", material);
            ToolStatReplacement replacement = replacements.getFirst();
            float dmg = replacement.attackDamage.get();
            float spd = replacement.attackSpeed.get();
            if (dmg != 99) args.set(2, dmg);
            if (spd != 99) args.set(3, spd);
        }
    }

    @ModifyArgs(
            method = "shovel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/Item$Properties;tool(Lnet/minecraft/world/item/ToolMaterial;Lnet/minecraft/tags/TagKey;FFF)Lnet/minecraft/world/item/Item$Properties;"
            )
    )
    private void modifyShovelStats(Args args, @Local(argsOnly = true) ToolMaterial material) {
        List<ToolStatReplacement> replacements = Main.CONFIG.toolStats.stream().filter(tsr ->
                ToolMaterialId.equal(tsr.material.get(), material) && tsr.type.get() == ToolType.SHOVEL
        ).toList();
        if (!replacements.isEmpty()) {
            Main.debugLog("Replacing stats for shovel {}", material);
            ToolStatReplacement replacement = replacements.getFirst();
            float dmg = replacement.attackDamage.get();
            float spd = replacement.attackSpeed.get();
            if (dmg != 99) args.set(2, dmg);
            if (spd != 99) args.set(3, spd);
        }
    }

    @ModifyArgs(
            method = "hoe",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/Item$Properties;tool(Lnet/minecraft/world/item/ToolMaterial;Lnet/minecraft/tags/TagKey;FFF)Lnet/minecraft/world/item/Item$Properties;"
            )
    )
    private void modifyHoeStats(Args args, @Local(argsOnly = true) ToolMaterial material) {
        List<ToolStatReplacement> replacements = Main.CONFIG.toolStats.stream().filter(tsr ->
                ToolMaterialId.equal(tsr.material.get(), material) && tsr.type.get() == ToolType.HOE
        ).toList();
        if (!replacements.isEmpty()) {
            Main.debugLog("Replacing stats for hoe {}", material);
            ToolStatReplacement replacement = replacements.getFirst();
            float dmg = replacement.attackDamage.get();
            float spd = replacement.attackSpeed.get();
            if (dmg != 99) args.set(2, dmg);
            if (spd != 99) args.set(3, spd);
        }
    }

    @ModifyArgs(
            method = "sword",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ToolMaterial;applySwordProperties(Lnet/minecraft/world/item/Item$Properties;FF)Lnet/minecraft/world/item/Item$Properties;"
            )
    )
    private void modifySwordStats(Args args, @Local(argsOnly = true) ToolMaterial material) {
        List<ToolStatReplacement> replacements = Main.CONFIG.toolStats.stream().filter(tsr ->
                ToolMaterialId.equal(tsr.material.get(), material) && tsr.type.get() == ToolType.SWORD
        ).toList();
        if (!replacements.isEmpty()) {
            Main.debugLog("Replacing stats for sword {}", material);
            ToolStatReplacement replacement = replacements.getFirst();
            float dmg = replacement.attackDamage.get();
            float spd = replacement.attackSpeed.get();
            if (dmg != 99) args.set(1, dmg);
            if (spd != 99) args.set(2, spd);
        }
    }
}
