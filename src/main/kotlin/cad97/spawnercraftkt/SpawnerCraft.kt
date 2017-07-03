package cad97.spawnercraftkt

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger

@Mod(
        modid = SpawnerCraft.modid,
        version = SpawnerCraft.version,
        name = SpawnerCraft.name,
        guiFactory = "cad97.spawnercraftkt.gui.GuiFactory",
        useMetadata = SpawnerCraft.useMetadata,
        acceptedMinecraftVersions = SpawnerCraft.acceptedMinecraftVersions
)
class SpawnerCraft {
    companion object {
        @Mod.Instance
        lateinit var instance: SpawnerCraft private set
        lateinit var logger: Logger private set
        const val modid = "spawnercraftkt"
        const val name = "SpawnerCraft"
        const val version = "<%version%>"
        const val useMetadata = true
        const val acceptedMinecraftVersions = "1.12"
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        logger = event.modLog
        Config.init(event.suggestedConfigurationFile)
        logger.info("PreInitialization complete")
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        logger.info("Initialization complete")
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        logger.info("PostInitialization complete")
    }
}
