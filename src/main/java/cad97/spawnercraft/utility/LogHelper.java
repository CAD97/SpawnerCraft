package cad97.spawnercraft.utility;

import cad97.spawnercraft.SpawnerCraft;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public final class LogHelper {
    private static Logger logger;

    public static void setLogger(Logger logger) {
        LogHelper.logger = logger;
    }

    private static void log(Level level, String message) {
        logger.log(level, "%s", "[" + SpawnerCraft.MOD_NAME + "] " + message);
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
