package handler;

import appender.LogAppender;
import enums.LogLevel;
import message.LogMessage;

import java.util.List;

public abstract class LogHandler {

    private LogHandler next;

    private List<LogAppender> appenders;

    public abstract boolean handles(LogLevel level);

    public void log(LogMessage logMsg) {
        notifyAllAppenders(logMsg);
    }

    public void setNext(LogHandler handler) {
        next = handler;
    }

    public void addAppender(LogAppender appender) {
        this.appenders.add(appender);
    }

    public void notifyAllAppenders(LogMessage logMsg) {

        for (LogAppender appender: appenders) {
            appender.append(logMsg);
        }

    }

}
