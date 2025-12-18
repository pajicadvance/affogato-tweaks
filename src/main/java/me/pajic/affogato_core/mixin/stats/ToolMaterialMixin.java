package me.pajic.affogato_core.mixin.stats;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.affogato_core.Main;
import me.pajic.affogato_core.util.ToolMaterialId;
import me.pajic.affogato_core.util.ToolStatReplacement;
import me.pajic.affogato_core.util.ToolType;
import net.minecraft.world.item.ToolMaterial;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ToolMaterial.class)
public class ToolMaterialMixin {

    @ModifyExpressionValue(
            method = "applyCommonProperties",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/item/ToolMaterial;durability:I",
                    opcode = Opcodes.GETFIELD
            )
    )
    private int modifyDurability(int original) {
        ToolMaterial material = (ToolMaterial) (Object) this;
        List<ToolStatReplacement> replacements = Main.CONFIG.toolStats.stream().filter(tsr ->
                ToolMaterialId.equal(tsr.material.get(), material) && tsr.type.get() == ToolType.ANY
        ).toList();
        if (!replacements.isEmpty()) {
            Main.debugLog("Replacing durability for {}", material);
            ToolStatReplacement replacement = replacements.getFirst();
            int durability = replacement.durability.get();
            if (durability != -1) return durability;
        }
        return original;
    }

    @ModifyExpressionValue(
            method = "applyToolProperties",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/item/ToolMaterial;speed:F",
                    opcode = Opcodes.GETFIELD
            )
    )
    private float modifyMiningSpeed(float original) {
        ToolMaterial material = (ToolMaterial) (Object) this;
        List<ToolStatReplacement> replacements = Main.CONFIG.toolStats.stream().filter(tsr ->
                ToolMaterialId.equal(tsr.material.get(), material) && tsr.type.get() == ToolType.ANY
        ).toList();
        if (!replacements.isEmpty()) {
            Main.debugLog("Replacing mining speed for {}", material);
            ToolStatReplacement replacement = replacements.getFirst();
            float miningSpeed = replacement.miningSpeed.get();
            if (miningSpeed != 99) return miningSpeed;
        }
        return original;
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void logIds(CallbackInfo ci) {
        ToolMaterial material = (ToolMaterial) (Object) this;
        ToolMaterialId.MATERIAL_IDS.add(ToolMaterialId.of(material));
    }
}