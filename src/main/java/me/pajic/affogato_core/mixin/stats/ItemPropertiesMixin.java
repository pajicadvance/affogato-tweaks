package me.pajic.affogato_core.mixin.stats;

import com.llamalad7.mixinextras.sugar.Local;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.util.*;
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
        List<WeaponStatReplacement> replacements = Main.CONFIG.weaponStats.stream().filter(wsr ->
                ToolMaterialId.equal(wsr.material.get(), material) && wsr.type.get() == WeaponType.PICKAXE
        ).toList();
        if (!replacements.isEmpty()) {
            Main.debugLog("Replacing stats for pickaxe {}", material);
            WeaponStatReplacement replacement = replacements.getFirst();
            if (replacement.modifyAttackDamage.get()) args.set(2, replacement.attackDamage.get());
            if (replacement.modifyAttackSpeed.get()) args.set(3, replacement.attackSpeed.get());
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
        List<WeaponStatReplacement> replacements = Main.CONFIG.weaponStats.stream().filter(wsr ->
                ToolMaterialId.equal(wsr.material.get(), material) && wsr.type.get() == WeaponType.AXE
        ).toList();
        if (!replacements.isEmpty()) {
            Main.debugLog("Replacing stats for axe {}", material);
            WeaponStatReplacement replacement = replacements.getFirst();
            if (replacement.modifyAttackDamage.get()) args.set(2, replacement.attackDamage.get());
            if (replacement.modifyAttackSpeed.get()) args.set(3, replacement.attackSpeed.get());
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
        List<WeaponStatReplacement> replacements = Main.CONFIG.weaponStats.stream().filter(wsr ->
                ToolMaterialId.equal(wsr.material.get(), material) && wsr.type.get() == WeaponType.SHOVEL
        ).toList();
        if (!replacements.isEmpty()) {
            Main.debugLog("Replacing stats for shovel {}", material);
            WeaponStatReplacement replacement = replacements.getFirst();
            if (replacement.modifyAttackDamage.get()) args.set(2, replacement.attackDamage.get());
            if (replacement.modifyAttackSpeed.get()) args.set(3, replacement.attackSpeed.get());
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
        List<WeaponStatReplacement> replacements = Main.CONFIG.weaponStats.stream().filter(wsr ->
                ToolMaterialId.equal(wsr.material.get(), material) && wsr.type.get() == WeaponType.HOE
        ).toList();
        if (!replacements.isEmpty()) {
            Main.debugLog("Replacing stats for hoe {}", material);
            WeaponStatReplacement replacement = replacements.getFirst();
            if (replacement.modifyAttackDamage.get()) args.set(2, replacement.attackDamage.get());
            if (replacement.modifyAttackSpeed.get()) args.set(3, replacement.attackSpeed.get());
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
        List<WeaponStatReplacement> replacements = Main.CONFIG.weaponStats.stream().filter(wsr ->
                ToolMaterialId.equal(wsr.material.get(), material) && wsr.type.get() == WeaponType.SWORD
        ).toList();
        if (!replacements.isEmpty()) {
            Main.debugLog("Replacing stats for sword {}", material);
            WeaponStatReplacement replacement = replacements.getFirst();
            if (replacement.modifyAttackDamage.get()) args.set(1, replacement.attackDamage.get());
            if (replacement.modifyAttackSpeed.get()) args.set(2, replacement.attackSpeed.get());
        }
    }
}
