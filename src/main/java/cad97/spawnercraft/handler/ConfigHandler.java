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
    public static boolean spawnerDropRequireSilk;

    public static void init(File configFile) {
        if (config == null) {
            config = new Configuration(configFile);
            loadConfig();
        }

        LogHelper.logInfo("ConfigHandler initialized.");
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(SpawnerCraft.MOD_ID)) {
            loadConfig();
        }
    }

    private static void loadConfig() {
        spawnerDropRequireSilk = config.get(Configuration.CATEGORY_GENERAL, "spawnerDropRequireSilk", false,
                "Does a Mob Spawner require Silk Touch to drop a Mob Cage?").getBoolean(false);

        if (config.hasChanged()) {
            config.save();
        }
    }
}
