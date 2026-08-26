import entity.LogMessage;
import enums.LogLevel;
import strategy.appender.LogAppender;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Logger {

    private final String name;
    private LogLevel level;
    private final Logger parent;
    private boolean additivity;
    private final List<LogAppender> appenders;

    public Logger(String name, Logger parent) {
        this.name = name;
        this.parent = parent;
        this.appenders = new CopyOnWriteArrayList<>();
        this.additivity = true;
    }

    public String getName() {return name;}

    public void setLevel(LogLevel minLevel) {this.level = minLevel;}

    public void setAdditivity(boolean additivity) {
        this.additivity = additivity;
    }

    public void addAppender(LogAppender appender) {
        appenders.add(appender);
    }

    public List<LogAppender> getAppenders() {return appenders;}

    private void callAppenders(LogMessage logMsg) {

        if (!appenders.isEmpty()) {
            LogManager.getInstance().getProcessor().process(logMsg, this.appenders);
        }
        if (additivity && parent != null) {
            parent.callAppenders(logMsg);
        }

    }

    private LogLevel getEffectiveLogLevel() {

        for (Logger logger = this; logger != null; logger = logger.parent) {
            if (logger.level != null) {
                return logger.level;
            }
        }
        // once out of the loop
        // no effective logger is found
        // in the whole hierarchy
        return LogLevel.DEBUG; // DEFAULT: least severe log level

    }

    public void log(String logMsg, LogLevel logLevel) {

        LogLevel effectiveLogLevel = getEffectiveLogLevel();
        if (effectiveLogLevel.lessThanOrEqualTo(logLevel)) {
            // equally or more severe logs are processed
            callAppenders(new LogMessage(logMsg, logLevel, this.name));
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

}
