package me.pajic.affogatotweaks;

import me.pajic.affogatotweaks.block.ModBlocks;
import me.pajic.affogatotweaks.effect.StatBoostEffects;
import me.pajic.affogatotweaks.item.ModItems;
import me.pajic.affogatotweaks.mixson.MixsonInitializer;
import me.pajic.affogatotweaks.particle.ModParticles;
import me.pajic.affogatotweaks.potion.ModPotions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.levelgen.structure.Structure;

public class Main implements ModInitializer {
    public static final String MOD_ID = "affogatotweaks";
    public static final boolean DEBUG = FabricLoader.getInstance().isDevelopmentEnvironment();
    public static final TagKey<Structure> OUTPOSTS = TagKey.create(
            Registries.STRUCTURE,
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "outposts")
    );
    public static EntityDataAccessor<Boolean> IS_LEADER = SynchedEntityData.defineId(Zombie.class, EntityDataSerializers.BOOLEAN);

    @Override
    public void onInitialize() {
        ModParticles.init();
        ModBlocks.init();
        ModItems.init();
        StatBoostEffects.init();
        ModPotions.init();
        MixsonInitializer.init();
        /*LavenderBookItem.registerForBook(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "affogato_guide"),
                new Item.Properties().stacksTo(1)
        );*/
    }
}
