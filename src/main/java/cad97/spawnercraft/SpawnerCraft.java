package cad97.spawnercraft;

import cad97.spawnercraft.proxy.IProxy;
import cad97.spawnercraft.utility.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings("DefaultAnnotationParam")
@Mod(modid = SpawnerCraft.MOD_ID, name = SpawnerCraft.MOD_NAME, version = SpawnerCraft.VERSION,
        guiFactory = SpawnerCraft.GUI_FACTORY_CLASS, dependencies = SpawnerCraft.DEPENDENCIES,
        acceptedMinecraftVersions = SpawnerCraft.ACCEPTED_MINECRAFT_VERSION)
public class SpawnerCraft {

    public static final String MOD_ID = "spawnercraft";
    public static final String MOD_NAME = "SpawnerCraft";

    static final String VERSION = "4.0.0";
    static final String GUI_FACTORY_CLASS = "cad97.spawnercraft.client.gui.GuiFactory"; // GuiFactory.class.getCanonicalName()
    static final String DEPENDENCIES = "";
    static final String ACCEPTED_MINECRAFT_VERSION = "[1.12]";

    @Mod.Instance
    static SpawnerCraft instance;

    @SidedProxy(clientSide = "cad97.spawnercraft.proxy.ClientProxy", serverSide = "cad97.spawnercraft.proxy.ServerProxy")
    private static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LogHelper.setLogger(event.getModLog());
        proxy.preInit(event);
        LogHelper.logInfo("preInit finished.");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        LogHelper.logInfo("init finished.");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
        LogHelper.logInfo("postInit finished.");
    }
}
