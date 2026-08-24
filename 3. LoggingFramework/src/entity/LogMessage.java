package entity;

import enums.LogLevel;

import java.time.LocalDateTime;

public class LogMessage {

    private String msg;
    private LogLevel level;
    private LocalDateTime timestamp;
    private String loggerName;
    private String thread;

    public LogMessage(String msg, LogLevel level, String loggerName) {
        this.msg = msg;
        this.level = level;
        timestamp = LocalDateTime.now();
        this.loggerName = loggerName;
        thread = Thread.currentThread().getName();
    }

    public String getMsg() {return msg;}

    public LogLevel getLevel() {return level;}

    public LocalDateTime getTimestamp() {return timestamp;}

    public String getLoggerName() {return loggerName;}

    public String getThread() {return thread;}

}
