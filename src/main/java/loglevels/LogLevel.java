package loglevels;

public enum LogLevel {
    INFO("INFO"),
    ERROR("ERROR"),
    DEBUG("DEBUG");

    private final String level;

    LogLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return level;
    }
}
