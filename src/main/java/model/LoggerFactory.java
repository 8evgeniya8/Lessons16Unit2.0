package model;

import errorcodes.ErrorMessages;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class LoggerFactory {
        @Contract(" -> new")
    static @NotNull Logger createLogger() {
        try {
            PrintWriter logWriter = new PrintWriter(new FileWriter("app.json", true));
            PrintWriter errorWriter = new PrintWriter(new FileWriter("error.log", true));
            return new Logger(logWriter, errorWriter);
        } catch (IOException e) {
            throw new RuntimeException(ErrorMessages.LOGGER_CREATION_ERROR.toString()
                    + ": " + e.getMessage(), e);
        }
    }
}
