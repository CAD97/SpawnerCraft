package cad97.spawnercraft.handler;

import cad97.spawnercraft.SpawnerCraft;
import cad97.spawnercraft.utility.LogHelper;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigHandler {
    public static final ConfigHandler instance = new ConfigHandler();

    private ConfigHandler() {
    }

    public static Configuration config;

    @SuppressWarnings("WeakerAccess")
    public static int spawnerDropSilkLevel;
    public static boolean spawnerCraftable;
    @SuppressWarnings("WeakerAccess")
    public static boolean dropsRequireFishing;

    public static void init(File configFile) {
        if (config == null) {
            config = new Configuration(configFile);
        }

        loadConfig();

        LogHelper.logInfo("ConfigHandler initialized.");
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(SpawnerCraft.MOD_ID)) {
            loadConfig();
        }
    }

    private static void loadConfig() {
        spawnerDropSilkLevel = config.get(
                Configuration.CATEGORY_GENERAL,
                "spawner_drop_required_silk_touch_level",
                0,
                "Required silk touch level to drop Empty Spawners from normal Mob Spawners.\n" +
                        "Set higher than obtainable silk touch (vanilla: >1) to disable."
        ).getInt(0);
        spawnerCraftable = config.get(
                Configuration.CATEGORY_GENERAL,
                "is_spawner_craftable",
                false,
                "Is it possible to craft an Empty Monster Spawner from iron bars?"
        ).setRequiresMcRestart(true).getBoolean();
        dropsRequireFishing = config.get(
                Configuration.CATEGORY_GENERAL,
                "drops_require_fishing",
                true,
                "Do Mob Essence drops require the use of a Mob Fishing Pole?"
        ).getBoolean();

        if (config.hasChanged()) {
            config.save();
        }
    }
}
