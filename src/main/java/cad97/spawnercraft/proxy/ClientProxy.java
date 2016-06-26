package cad97.spawnercraft.proxy;

import cad97.spawnercraft.block.SpawnerCraftBlocks;

public final class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        super.preInit();
        SpawnerCraftBlocks.registerModels();
    }
}
