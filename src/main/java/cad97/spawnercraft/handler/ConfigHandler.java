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

    public static boolean witherSkeletonSoul;
    public static boolean straySoul;
    public static boolean huskSoul;
    public static boolean elderGuardianSoul;
    public static boolean donkeySoul;
    public static boolean muleSoul;
    public static boolean skeletonHorseSoul;

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
        spawnerDropSilkLevel = config.get(Configuration.CATEGORY_GENERAL, "spawnerDropSilkLevel", 0,
                "Required silk touch level to drop Empty Spawners from normal Mob Spawners.\n" +
                        "Set higher than obtainable silk touch (vanilla: >1) to disable.").getInt(0);
        spawnerCraftable = config.get(Configuration.CATEGORY_GENERAL, "spawnerCraftable", false,
                "Is it possible to craft an Empty Monster Spawner from iron bars?").setRequiresMcRestart(true).getBoolean();
        dropsRequireFishing = config.get(Configuration.CATEGORY_GENERAL, "dropsRequireFishing", true,
                "Do Mob Essence drops require the use of a Mob Fishing Pole?").getBoolean();

        witherSkeletonSoul = config.get("Custom Souls", "witherSkeletonSoul", true,
                "Is there a Mob Soul for Wither Skeletons?").setRequiresMcRestart(true).getBoolean();
        straySoul = config.get("Custom Souls", "straySoul", true,
                "Is there a Mob Soul for Strays?").setRequiresMcRestart(true).getBoolean();
        huskSoul = config.get("Custom Souls", "huskSoul", true,
                "Is there a Mob Soul for Husks?").setRequiresMcRestart(true).getBoolean();
        elderGuardianSoul = config.get("Custom Souls", "elderGuardianSoul", false,
                "Is there a Mob Soul for Elder Guardians?").setRequiresMcRestart(true).getBoolean();
        donkeySoul = config.get("Custom Souls", "donkeySoul", true,
                "Is there a Mob Soul for Donkeys?").setRequiresMcRestart(true).getBoolean();
        muleSoul = config.get("Custom Souls", "muleSoul", true,
                "Is there a Mob Soul for Mules?").setRequiresMcRestart(true).getBoolean();
        skeletonHorseSoul = config.get("Custom Souls", "skeletonHorseSoul", false,
                "Is there a Mob Soul for Skeleton Horses?").setRequiresMcRestart(true).getBoolean();

        if (config.hasChanged()) {
            config.save();
        }
    }
}
