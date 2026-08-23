package handler;

import enums.LogLevel;

public class WarnLogHandler extends LogHandler {

    @Override
    public boolean handles(LogLevel level) {
        return LogLevel.WARN.equals(level);
    }

}
