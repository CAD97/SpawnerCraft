package cad97.spawnercraftkt

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(modid = SpawnerCraft.modid, version = SpawnerCraft.version)
class SpawnerCraft {
    companion object {
        const val modid = "spawnercraftkt"
        const val version = "<%version%>"
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        event.modLog.info("Hello from Kotlin!")
    }
}