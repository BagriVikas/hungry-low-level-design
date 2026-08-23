package appender;

import formatter.LogFormatter;
import message.LogMessage;

public abstract class LogAppender {

    protected LogFormatter formatter;

    public void setFormatter(LogFormatter formatter) {
        this.formatter = formatter;
    }

    public abstract void append(LogMessage logMsg);

}
