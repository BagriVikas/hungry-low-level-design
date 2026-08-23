package factory;

import appender.ConsoleLogAppender;
import appender.FileLogAppender;
import appender.LogAppender;
import enums.Appender;

public class LogAppenderFactory {

    public static LogAppender getLogAppender(Appender appender) {

        LogAppender logAppender = null;
        if (Appender.CONSOLE.equals(appender)) {
            logAppender = new ConsoleLogAppender();
        } else if (Appender.FILE.equals(appender)) {
            logAppender = new FileLogAppender();
        }
        // database appender not implemented yet
        return logAppender;

    }

}
