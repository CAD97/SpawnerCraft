package cad97.spawnercraft.utility;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public final class LogHelper {
    private static Logger logger;

    public static void setLogger(Logger logger) {
        LogHelper.logger = logger;
    }

    public static void logFatal(String message) {
        logger.log(Level.FATAL, message);
    }

    public static void logError(String message) {
        logger.log(Level.ERROR, message);
    }

    public static void logWarn(String message) {
        logger.log(Level.WARN, message);
    }

    public static void logInfo(String message) {
        logger.log(Level.INFO, message);
    }

    public static void logDebug(String message) {
        logger.log(Level.DEBUG, message);
    }

}
