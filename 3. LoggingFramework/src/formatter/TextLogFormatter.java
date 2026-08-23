package formatter;

import message.LogMessage;

public class TextLogFormatter implements LogFormatter {

    @Override
    public String format(LogMessage logMsg) {
        return String.format("%s %s %s", logMsg.getTimestamp().toString(), logMsg.getLevel().toString(), logMsg.getMsg());
    }

}
