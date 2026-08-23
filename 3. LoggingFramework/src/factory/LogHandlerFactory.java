package factory;

import enums.LogLevel;
import handler.*;

public class LogHandlerFactory {

    public static LogHandler getLogHandler(LogLevel level) {

        LogHandler handler = null;
        if (LogLevel.DEBUG.equals(level)) {
            handler = new DebugLogHandler();
        } else if (LogLevel.INFO.equals(level)) {
            handler = new InfoLogHandler();
        } else if (LogLevel.WARN.equals(level)) {
            handler = new WarnLogHandler();
        } else if (LogLevel.ERROR.equals(level)) {
            handler = new ErrorLogHandler();
        } else if (LogLevel.FATAL.equals(level)) {
            handler = new FatalLogHandler();
        }
        return handler;

    }

}
