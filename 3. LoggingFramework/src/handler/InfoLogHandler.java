package handler;

import enums.LogLevel;

public class InfoLogHandler extends LogHandler {

    @Override
    public boolean handles(LogLevel level) {
        return LogLevel.INFO.equals(level);
    }

}
