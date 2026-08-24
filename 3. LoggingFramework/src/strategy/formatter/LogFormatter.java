package strategy.formatter;

import entity.LogMessage;

public interface LogFormatter {

    String format(LogMessage logMsg);

}
