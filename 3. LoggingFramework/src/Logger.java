import appender.LogAppender;
import enums.LogLevel;
import handler.LogHandler;
import message.LogMessage;

import java.time.Instant;

public class Logger {

    private static Logger logger; // singleton
    private LogHandler headHandler; // HEAD of linked list
    private LogHandler tailHandler; // TAIL of linked list

    public synchronized static Logger getInstance() {

        if (null == logger) {
            logger = new Logger();
        }
        return logger;

    }

    public void log(String msg, LogLevel level) {

        LogMessage logMsg = new LogMessage(level, msg, Instant.now());
        LogHandler temp = headHandler;
        while (temp != null) {
            if (temp.handles(level)) {
                temp.log(logMsg);
                break;
            }
            temp = temp.getNext();
        }

    }

    public void debug(String msg) {
        log(msg, LogLevel.DEBUG);
    }

    public void info(String msg) {
        log(msg, LogLevel.INFO);
    }

    public void warn(String msg) {
        log(msg, LogLevel.WARN);
    }

    public void error(String msg) {
        log(msg, LogLevel.ERROR);
    }

    public void fatal(String msg) {
        log(msg, LogLevel.FATAL);
    }

    public void addHandler(LogHandler handler) {

        if (headHandler == null) {
            headHandler = tailHandler = handler;
        } else {
            tailHandler.setNext(handler);
            tailHandler = handler;
        }

    }

    public void addAppenderForHandler(LogAppender appender, LogLevel level) {

        // find appropriate log handler
        // add 'appender' as one of the observer
        LogHandler temp = headHandler;
        while (temp != null) {
            if (temp.handles(level)) {
                temp.addAppender(appender);
                break;
            }
            temp = temp.getNext();
        }

    }

}
