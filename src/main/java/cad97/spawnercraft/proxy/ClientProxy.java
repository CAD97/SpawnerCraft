package cad97.spawnercraft.proxy;

import cad97.spawnercraft.init.SpawnerCraftBlocks;
import cad97.spawnercraft.init.SpawnerCraftItems;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public final class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        SpawnerCraftBlocks.registerModels();
        SpawnerCraftItems.registerModels();
    }
}
