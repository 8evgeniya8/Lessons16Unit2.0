package errorcodes;

public enum ErrorMessages {
    LOG_FILE_WRITE_ERROR("Помилка при записі в лог-файл"),
    ERROR_FILE_WRITE_ERROR("Помилка при записі помилки в error.log"),
    LOGGER_CREATION_ERROR("Помилка при створенні логгера");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
