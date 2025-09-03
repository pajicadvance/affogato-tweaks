package me.pajic.affogatotweaks;

import me.pajic.affogatotweaks.block.ModBlocks;
import me.pajic.affogatotweaks.datapack.ModDatapacks;
import me.pajic.affogatotweaks.effect.StatBoostEffects;
import me.pajic.affogatotweaks.item.ModItems;
import me.pajic.affogatotweaks.mixson.MixsonInitializer;
import me.pajic.affogatotweaks.particle.ModParticles;
import me.pajic.affogatotweaks.poi.ModPoiTypes;
import me.pajic.affogatotweaks.tag.ModTags;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class Main implements ModInitializer {
    public static final String MOD_ID = "affogatotweaks";
    public static final boolean DEBUG = FabricLoader.getInstance().isDevelopmentEnvironment();
    public static EntityDataAccessor<Boolean> IS_LEADER = SynchedEntityData.defineId(Zombie.class, EntityDataSerializers.BOOLEAN);

    @Override
    public void onInitialize() {
        ModDatapacks.init();
        ModTags.init();
        ModParticles.init();
        ModBlocks.init();
        ModItems.init();
        ModPoiTypes.init();
        StatBoostEffects.init();
        MixsonInitializer.init();
        /*LavenderBookItem.registerForBook(
                ResourceLocation.fromNamespaceAndPath(MOD_ID, "affogato_guide"),
                new Item.Properties().stacksTo(1)
        );*/
    }

    public static ResourceLocation withModNamespace(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
