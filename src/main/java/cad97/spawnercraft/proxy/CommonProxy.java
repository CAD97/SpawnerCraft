package cad97.spawnercraft.proxy;

import cad97.spawnercraft.block.SpawnerCraftBlocks;

import javax.annotation.OverridingMethodsMustInvokeSuper;

abstract class CommonProxy implements IProxy {

    @Override
    @OverridingMethodsMustInvokeSuper
    public void preInit() {
        SpawnerCraftBlocks.registerBlocks();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void init() {
        // stub
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void postInit() {
        // stub
    }

}
