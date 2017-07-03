package cad97.spawnercraftkt.proxy

import cad97.spawnercraftkt.Config
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import javax.annotation.OverridingMethodsMustInvokeSuper

abstract class Proxy {
    @OverridingMethodsMustInvokeSuper
    fun preInit(event: FMLPreInitializationEvent) {
        Config.init(event.suggestedConfigurationFile)
    }

    @OverridingMethodsMustInvokeSuper
    fun init(event: FMLInitializationEvent) {
    }

    @OverridingMethodsMustInvokeSuper
    fun postInit(event: FMLPostInitializationEvent) {

    }
}
