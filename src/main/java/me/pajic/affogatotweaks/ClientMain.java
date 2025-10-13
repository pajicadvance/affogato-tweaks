package me.pajic.affogatotweaks;

import me.pajic.affogatotweaks.block.ModBlocks;
import me.pajic.affogatotweaks.mixson.MixsonClientInitializer;
import me.pajic.affogatotweaks.oxidation.OxidationData;
import me.pajic.affogatotweaks.oxidation.OxidationUtil;
import me.pajic.affogatotweaks.particle.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ClientMain implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MixsonClientInitializer.init();
        ItemProperties.registerGeneric(
                Main.withModNamespace("oxidation"),
                (stack, cl, le, i) -> switch (OxidationUtil.getItemOxidation(stack)) {
                    case 3 -> 1;
                    case 2 -> 0.75F;
                    case 1 -> 0.25F;
                    default -> 0;
                }
        );
        ParticleFactoryRegistry.getInstance().register(ModParticles.COPPER_FLAME, FlameParticle.Provider::new);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                ModBlocks.COPPER_TORCH, ModBlocks.COPPER_WALL_TORCH,
                ModBlocks.EXPOSED_LIGHTNING_ROD, ModBlocks.WEATHERED_LIGHTNING_ROD, ModBlocks.OXIDIZED_LIGHTNING_ROD,
                ModBlocks.WAXED_LIGHTNING_ROD, ModBlocks.WAXED_EXPOSED_LIGHTNING_ROD, ModBlocks.WAXED_WEATHERED_LIGHTNING_ROD, ModBlocks.WAXED_OXIDIZED_LIGHTNING_ROD,
                ModBlocks.COPPER_LANTERN, ModBlocks.EXPOSED_COPPER_LANTERN, ModBlocks.WEATHERED_COPPER_LANTERN, ModBlocks.OXIDIZED_COPPER_LANTERN,
                ModBlocks.WAXED_COPPER_LANTERN, ModBlocks.WAXED_EXPOSED_COPPER_LANTERN, ModBlocks.WAXED_WEATHERED_COPPER_LANTERN, ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutoutMipped(),
                ModBlocks.COPPER_BARS, ModBlocks.EXPOSED_COPPER_BARS, ModBlocks.WEATHERED_COPPER_BARS, ModBlocks.OXIDIZED_COPPER_BARS,
                ModBlocks.WAXED_COPPER_BARS, ModBlocks.WAXED_EXPOSED_COPPER_BARS, ModBlocks.WAXED_WEATHERED_COPPER_BARS, ModBlocks.WAXED_OXIDIZED_COPPER_BARS,
                ModBlocks.COPPER_CHAIN, ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.WEATHERED_COPPER_CHAIN, ModBlocks.OXIDIZED_COPPER_CHAIN,
                ModBlocks.WAXED_COPPER_CHAIN, ModBlocks.WAXED_EXPOSED_COPPER_CHAIN, ModBlocks.WAXED_WEATHERED_COPPER_CHAIN, ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN
        );
    }

    public static ResourceLocation getAssetId(ItemStack stack, ResourceLocation original) {
        if (stack.is(OxidationData.OXIDIZABLE)) {
            String state = switch (OxidationUtil.getItemOxidation(stack)) {
                case 1 -> "_exposed";
                case 2 -> "_weathered";
                case 3 -> "_oxidized";
                default -> "";
            };
            if (!state.isEmpty()) return Main.withModNamespace(original.getPath().replace(".", state + "."));
        }
        return original;
    }
}
