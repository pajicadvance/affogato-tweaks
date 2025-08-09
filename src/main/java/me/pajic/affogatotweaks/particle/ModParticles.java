package me.pajic.affogatotweaks.particle;

import me.pajic.affogatotweaks.Main;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class ModParticles {
    public static final SimpleParticleType COPPER_FLAME = Registry.register(
            BuiltInRegistries.PARTICLE_TYPE,
            ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "copper_flame"),
            FabricParticleTypes.simple()
    );

    public static void init() {}
}
