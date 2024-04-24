package me.pajic.affogatotweaks.mixin.charmony;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import svenhjol.charmony.api.iface.IVariantMaterial;
import svenhjol.charmony.block.CharmonyStairBlock;

@Mixin(CharmonyStairBlock.class)
public abstract class CharmonyStairBlockMixin extends StairBlock {

    public CharmonyStairBlockMixin(BlockState baseState, Properties properties) {
        super(baseState, properties);
    }

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/StairBlock;<init>(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V"), index = 1)
    private static Properties modifyStairProperties(Properties properties, @Local(argsOnly = true) IVariantMaterial material) {
        return material.blockProperties().strength(2.0F, 3.0F);
    }
}
