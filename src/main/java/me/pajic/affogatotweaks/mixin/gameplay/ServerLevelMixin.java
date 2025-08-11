package me.pajic.affogatotweaks.mixin.gameplay;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.pajic.affogatotweaks.poi.ModPoiTypes;
import me.pajic.affogatotweaks.tag.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {

    @WrapOperation(
            method = "tickChunk",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"
            )
    )
    private boolean modifyLightningRodCheck(BlockState instance, Block block, Operation<Boolean> original) {
        return original.call(instance, block) || instance.is(ModTags.LIGHTNING_ROD_VARIANTS);
    }

    @WrapOperation(
            method = "method_31421",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/core/Holder;is(Lnet/minecraft/resources/ResourceKey;)Z"
            )
    )
    private static boolean modifyLightningRodCheck(Holder<PoiType> instance, ResourceKey<PoiType> resourceKey, Operation<Boolean> original) {
        ResourceLocation rl = BuiltInRegistries.POINT_OF_INTEREST_TYPE.getKey(ModPoiTypes.LIGHTNING_ROD_VARIANTS);
        return (rl != null && instance.is(rl)) || original.call(instance, resourceKey);
    }
}
