package message;

import enums.LogLevel;

import java.time.Instant;

public class LogMessage {

    private LogLevel level;
    private String msg;
    private Instant timestamp;

    public LogMessage(LogLevel level, String msg, Instant timestamp) {
        this.level = level;
        this.msg = msg;
        this.timestamp = timestamp;
    }

    public LogLevel getLevel() {return level;}

    public String getMsg() {return msg;}

    public Instant getTimestamp() {return timestamp;}

}
