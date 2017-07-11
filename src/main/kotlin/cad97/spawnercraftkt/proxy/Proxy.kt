package cad97.spawnercraftkt.proxy

import cad97.spawnercraftkt.Config
import cad97.spawnercraftkt.SpawnerCraft
import cad97.spawnercraftkt.tileentity.TileEntityMobBlock
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import javax.annotation.OverridingMethodsMustInvokeSuper

abstract class Proxy {
    @OverridingMethodsMustInvokeSuper
    open fun preInit(event: FMLPreInitializationEvent) {
        Config.init(event.suggestedConfigurationFile)
    }

    @OverridingMethodsMustInvokeSuper
    open fun init(event: FMLInitializationEvent) {
        GameRegistry.registerTileEntity(TileEntityMobBlock::class.java, "${SpawnerCraft.modid}:${TileEntityMobBlock.id}")
    }

    @OverridingMethodsMustInvokeSuper
    open fun postInit(event: FMLPostInitializationEvent) {
    }
}
