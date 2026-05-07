package me.pajic.affogato_core;

import net.fabricmc.loader.api.FabricLoader;

public class CompatFlags {
    public static final boolean IRIS_LOADED = FabricLoader.getInstance().isModLoaded("iris");
    public static final boolean FD_LOADED = FabricLoader.getInstance().isModLoaded("farmersdelight");
    public static final boolean ITEMSWAPPER_LOADED = FabricLoader.getInstance().isModLoaded("itemswapper");
    public static final boolean SERENE_WILD_LOADED = FabricLoader.getInstance().isModLoaded("serenewild");
    public static final boolean WILDER_WILD_LOADED = FabricLoader.getInstance().isModLoaded("wilderwild");
    public static final boolean TRAILIER_TALES_LOADED = FabricLoader.getInstance().isModLoaded("trailiertales");
    public static final boolean TRAVERSE_LOADED = FabricLoader.getInstance().isModLoaded("traverse");
    public static final boolean NULLSCAPE_LOADED = FabricLoader.getInstance().isModLoaded("nullscape");
    public static final boolean MASTERCUTTER_LOADED = FabricLoader.getInstance().isModLoaded("mr_mastercutter");
    public static final boolean TOIL_AND_TROUBLE_LOADED = FabricLoader.getInstance().isModLoaded("toil_and_trouble");
    public static final boolean HORSEMAN_LOADED = FabricLoader.getInstance().isModLoaded("horseman");
    public static final boolean VARIANTS_AND_VENTURES_LOADED = FabricLoader.getInstance().isModLoaded("variantsandventures");
}
