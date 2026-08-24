package strategy.appender;

import entity.LogMessage;
import enums.LogLevel;
import strategy.formatter.LogFormatter;
import strategy.formatter.SimpleTextFormatter;

public abstract class LogAppender {

    protected LogFormatter logFormatter;

    public LogAppender() {
        this.logFormatter = new SimpleTextFormatter();
    }

    public void setLogFormatter(LogFormatter logFormatter) {
        this.logFormatter = logFormatter;
    }

    public LogFormatter getLogFormatter() {return logFormatter;}

    public abstract void append(LogMessage logMsg);

    public abstract void close();

}
