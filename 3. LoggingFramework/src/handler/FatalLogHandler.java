package handler;

import enums.LogLevel;

public class FatalLogHandler extends LogHandler {

    @Override
    public boolean handles(LogLevel level) {
        return LogLevel.FATAL.equals(level);
    }

}
