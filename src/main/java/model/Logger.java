package model;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import errorcodes.ErrorMessages;
import loglevels.LogLevel;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class Logger {
    private static volatile Logger instance;
    private final PrintWriter logWriter;
    private final PrintWriter errorWriter;

    Logger(PrintWriter logWriter, PrintWriter errorWriter) {
        this.logWriter = logWriter;
        this.errorWriter = errorWriter;
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = LoggerFactory.createLogger();
                }
            }
        }
        return instance;
    }

    private void log(LogLevel level, String message) {
        JSONObject logEntry = new JSONObject();
        logEntry.put("timestamp", LocalDateTime
                .now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        logEntry.put("level", level.toString());
        logEntry.put("message", message);

        try {
            logWriter.println(logEntry.toString());
            logWriter.flush();
        } catch (Exception e) {
            logErrorToFileAndConsole(e);
        }
    }

    private void logErrorToFileAndConsole(@NotNull Exception e) {
        String errorMessage = ErrorMessages.LOG_FILE_WRITE_ERROR.toString()
                + ": " + e.getMessage();
        System.err.println(errorMessage);

        try {
            e.printStackTrace(errorWriter);
            errorWriter.flush();
        } catch (Exception ex) {
            System.err.println(ErrorMessages.ERROR_FILE_WRITE_ERROR.toString()
                    + ": " + ex.getMessage());
        }
    }

    public void closeLoggers() {
        logWriter.close();
        errorWriter.close();
    }

    public void logInfo(String message) {
        log(LogLevel.INFO, message);
    }

    public void logError(String message) {
        log(LogLevel.ERROR, message);
    }

    public void logDebug(String message) {
        log(LogLevel.DEBUG, message);
    }
}

