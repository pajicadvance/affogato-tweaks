package me.pajic.affogato_core;

import net.fabricmc.loader.api.FabricLoader;

public class CompatFlags {
    public static final boolean FD_LOADED = FabricLoader.getInstance().isModLoaded("farmersdelight");
    public static final boolean ITEMSWAPPER_LOADED = FabricLoader.getInstance().isModLoaded("itemswapper");
    public static final boolean SERENE_WILD_LOADED = FabricLoader.getInstance().isModLoaded("serenewild");
    public static final boolean WILDER_WILD_LOADED = FabricLoader.getInstance().isModLoaded("wilderwild");
    public static final boolean MASTERCUTTER_LOADED = FabricLoader.getInstance().isModLoaded("mr_mastercutter");
}
