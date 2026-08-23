package appender;

import formatter.LogFormatter;
import message.LogMessage;

public class ConsoleLogAppender extends LogAppender {

    @Override
    public void append(LogMessage logMsg) {

        // format msg
        String formattedMsg = formatter.format(logMsg);
        // append 'formattedMsg' to the console
        System.out.println(formattedMsg);

    }

}
