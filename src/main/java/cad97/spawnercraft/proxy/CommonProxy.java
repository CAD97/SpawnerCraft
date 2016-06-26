package cad97.spawnercraft.proxy;

import cad97.spawnercraft.block.SpawnerCraftBlocks;

abstract class CommonProxy implements IProxy {

    @Override
    public void preInit() {
        SpawnerCraftBlocks.init();
    }

    @Override
    public void init() {
        // stub
    }

    @Override
    public void postInit() {
        // stub
    }

}
