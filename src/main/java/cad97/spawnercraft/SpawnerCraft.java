package cad97.spawnercraft;

import cad97.spawnercraft.proxy.IProxy;
import cad97.spawnercraft.utility.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SpawnerCraft.MOD_ID, name = SpawnerCraft.MOD_NAME, version = SpawnerCraft.VERSION)
public class SpawnerCraft {

    public static final String MOD_ID = "spawnercraft";
    public static final String MOD_NAME = "SpawnerCraft";
    public static final String VERSION = "3.0";

    @Mod.Instance
    static SpawnerCraft instance;

    @SidedProxy(clientSide = "cad97.spawnercraft.proxy.ClientProxy", serverSide = "cad97.spawnercraft.proxy.ServerProxy")
    private static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
        LogHelper.logInfo("preInit finished.");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        LogHelper.logInfo("init finished.");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
        LogHelper.logInfo("postInit finished.");
    }
}
