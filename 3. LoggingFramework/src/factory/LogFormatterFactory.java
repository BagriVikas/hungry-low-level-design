package factory;

import enums.Formatter;
import formatter.JsonLogFormatter;
import formatter.LogFormatter;
import formatter.TextLogFormatter;

public class LogFormatterFactory {

    public static LogFormatter getLogFormatter(Formatter formatter) {

        LogFormatter logFormatter = null;
        if (Formatter.TEXT.equals(formatter)) {
            logFormatter = new TextLogFormatter();
        } else if (Formatter.JSON.equals(formatter)) {
            logFormatter = new JsonLogFormatter();
        }
        return logFormatter;

    }

}
