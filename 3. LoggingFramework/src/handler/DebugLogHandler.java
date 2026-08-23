package handler;

import enums.LogLevel;

public class DebugLogHandler extends LogHandler {

    @Override
    public boolean handles(LogLevel level) {
        return LogLevel.DEBUG.equals(level);
    }

}
