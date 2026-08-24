package strategy.appender;

import entity.LogMessage;

public class ConsoleLogAppender extends LogAppender {

    @Override
    public void append(LogMessage logMsg) {

        // simply print msg in console
        String formattedMsg = logFormatter.format(logMsg);
        System.out.println(formattedMsg);

    }

    @Override
    public void close() {
        // no need to close the console resource
    }

}
