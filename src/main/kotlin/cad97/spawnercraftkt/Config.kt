package cad97.spawnercraftkt

import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.client.event.ConfigChangedEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.io.File

@Mod.EventBusSubscriber(modid = SpawnerCraft.modid)
object Config {
    val defaultDisabledMobs = arrayOf<String>()

    lateinit var config: Configuration private set

    var spawnerDropSilkLevel = 0
        private set
    var spawnerCraftable = false
        private set
    var dropsRequireFishing = true
        private set
    var isBlackList = true
        private set

    lateinit var mobEssenceToggleList: List<String> private set
    lateinit var eggMapping: Map<String, String> private set

    fun init(configFile: File) {
        config = Configuration(configFile)
        loadConfig()
        config.save()
        SpawnerCraft.logger.info("Config initialized")
    }

    fun loadConfig() {
        spawnerDropSilkLevel = config.get(
                Configuration.CATEGORY_GENERAL,
                "Spawner Silk Touch Level",
                0,
                "Required silk touch level to drop Empty Spawners from normal Mob Spawners.\n" +
                        "Set higher than obtainable silk touch (vanilla: >1) to disable.",
                0, Int.MAX_VALUE
        ).int
        spawnerCraftable = config.get(
                Configuration.CATEGORY_GENERAL,
                "Spawner Craftable",
                false,
                "Is it possible to craft an Empty Monster Spawner from iron bars?"
        ).setRequiresMcRestart(true).boolean
        dropsRequireFishing = config.get(
                Configuration.CATEGORY_GENERAL,
                "Essence Requires Fishing",
                true,
                "Do Mob Essence drops require the use of a Mob Fishing Pole?"
        ).boolean
        mobEssenceToggleList = config.get(
                Configuration.CATEGORY_GENERAL,
                "Essence Drop Black/White List",
                defaultDisabledMobs,
                "Mobs which should not have essence (blacklist mode)\n" +
                        "Mobs which should have essence (whitelist mode)"
        ).stringList.toList()
        isBlackList = config.get(
                Configuration.CATEGORY_GENERAL,
                "Drop List is Blacklist",
                true,
                "Essence Toggle List should operate in blacklist mode (true) or whitelist mode (false)"
        ).boolean

        eggMapping = config.getCategory("Egg Mapping").toMap().mapValues { it.value.string }

        if (config.hasChanged()) config.save()
    }

    @JvmStatic
    @SubscribeEvent
    fun configurationChanged(event: ConfigChangedEvent.OnConfigChangedEvent) {
        SpawnerCraft.logger.info("Configuration Changed")
        if (event.modID.equals(SpawnerCraft.modid, ignoreCase = true)) loadConfig()
    }
}