package cad97.spawnercraft.proxy;

import cad97.spawnercraft.handler.ConfigHandler;
import cad97.spawnercraft.handler.DropsListener;
import cad97.spawnercraft.init.SpawnerCraftBlocks;
import cad97.spawnercraft.init.SpawnerCraftItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.annotation.OverridingMethodsMustInvokeSuper;

abstract class CommonProxy implements IProxy {

    @Override
    @OverridingMethodsMustInvokeSuper
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(ConfigHandler.instance);
        MinecraftForge.EVENT_BUS.register(DropsListener.instance);
        SpawnerCraftBlocks.registerBlocks();
        SpawnerCraftItems.registerItems();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void init(FMLInitializationEvent event) {
        // stub
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void postInit(FMLPostInitializationEvent event) {
        // stub
    }

}
