package strategy.formatter;

import entity.LogMessage;

import java.time.format.DateTimeFormatter;

public class SimpleTextFormatter implements LogFormatter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public String format(LogMessage logMsg) {

        return String.format(
                "%s [%s] %s - %s: %s\n",
                logMsg.getTimestamp().format(DATE_TIME_FORMATTER),
                logMsg.getThread(),
                logMsg.getLevel().toString(),
                logMsg.getLoggerName(),
                logMsg.getMsg()
        );

    }

}
