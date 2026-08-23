package handler;

import enums.LogLevel;

public class ErrorLogHandler extends LogHandler {

    @Override
    public boolean handles(LogLevel level) {
        return LogLevel.ERROR.equals(level);
    }

}
