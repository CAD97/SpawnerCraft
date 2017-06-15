package cad97.spawnercraft.handler;

import cad97.spawnercraft.SpawnerCraft;
import cad97.spawnercraft.utility.LogHelper;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.actors.threadpool.Arrays;

import java.io.File;
import java.util.List;

public class ConfigHandler {
    public static final ConfigHandler instance = new ConfigHandler();
    private static String[] DEFAULT_DISABLED_MOBS = {};

    private ConfigHandler() {
    }

    public static Configuration config;

    @SuppressWarnings("WeakerAccess")
    public static int spawnerDropSilkLevel;
    public static boolean spawnerCraftable;
    @SuppressWarnings("WeakerAccess")
    public static boolean dropsRequireFishing;
    @SuppressWarnings("WeakerAccess")
    public static List disabledMobs = Arrays.asList(DEFAULT_DISABLED_MOBS);

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
                "Silk Touch for Spawner Drop",
                0,
                "Required silk touch level to drop Empty Spawners from normal Mob Spawners.\n" +
                        "Set higher than obtainable silk touch (vanilla: >1) to disable."
        ).getInt(0);
        spawnerCraftable = config.get(
                Configuration.CATEGORY_GENERAL,
                "Is Empty Spawner Craftable",
                false,
                "Is it possible to craft an Empty Monster Spawner from iron bars?"
        ).setRequiresMcRestart(true).getBoolean();
        dropsRequireFishing = config.get(
                Configuration.CATEGORY_GENERAL,
                "Essence Drops Require Fishing",
                true,
                "Do Mob Essence drops require the use of a Mob Fishing Pole?"
        ).getBoolean();
        disabledMobs = Arrays.asList(config.get(
                Configuration.CATEGORY_GENERAL,
                "Disabled Essence",
                DEFAULT_DISABLED_MOBS,
                "Mobs which should never drop essence."
        ).getStringList());

        if (config.hasChanged()) {
            config.save();
        }
    }
}
