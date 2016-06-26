package cad97.spawnercraft.proxy;

import cad97.spawnercraft.init.SpawnerCraftBlocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public final class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        SpawnerCraftBlocks.registerModels();
    }
}
