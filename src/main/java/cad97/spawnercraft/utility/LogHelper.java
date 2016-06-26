package cad97.spawnercraft.utility;

import cad97.spawnercraft.SpawnerCraft;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public final class LogHelper {

    private static void log(Level level, String message) {
        FMLLog.log(level, "%s", "[" + SpawnerCraft.MOD_NAME + "] " + message);
    }

    public static void logFatal(String message) {
        log(Level.FATAL, message);
    }

    public static void logError(String message) {
        log(Level.ERROR, message);
    }

    public static void logWarn(String message) {
        log(Level.WARN, message);
    }

    public static void logInfo(String message) {
        log(Level.INFO, message);
    }

    public static void logDebug(String message) {
        log(Level.DEBUG, message);
    }

}
