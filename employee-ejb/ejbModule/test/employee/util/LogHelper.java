package test.employee.util;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LogHelper {

    public static void log(Logger logger, Level level, String message, Object... params) {
        LogRecord logRecord = new LogRecord(level, message);
        logRecord.setParameters(params);
        logger.log(logRecord);
    }

    public static void info(Logger logger, String message, Object... params) {
        LogRecord logRecord = new LogRecord(Level.INFO, message);
        logRecord.setParameters(params);
        logger.log(logRecord);
    }
}
