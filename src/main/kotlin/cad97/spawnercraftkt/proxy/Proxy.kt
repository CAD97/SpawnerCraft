package cad97.spawnercraftkt.proxy

import cad97.spawnercraftkt.Config
import cad97.spawnercraftkt.init.SpawnerCraftRecipes
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import javax.annotation.OverridingMethodsMustInvokeSuper

abstract class Proxy {
    @OverridingMethodsMustInvokeSuper
    open fun preInit(event: FMLPreInitializationEvent) {
        Config.init(event.suggestedConfigurationFile)
    }

    @OverridingMethodsMustInvokeSuper
    open fun init(event: FMLInitializationEvent) {
        SpawnerCraftRecipes.removeDisabledRecipes()
    }

    @OverridingMethodsMustInvokeSuper
    open fun postInit(event: FMLPostInitializationEvent) {
    }
}
