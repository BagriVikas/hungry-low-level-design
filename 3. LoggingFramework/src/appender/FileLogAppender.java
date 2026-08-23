package appender;

import formatter.LogFormatter;
import message.LogMessage;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileLogAppender extends LogAppender {

    private static final String fileName = "log_file.txt";

    @Override
    public void append(LogMessage logMsg) {

        // format msg
        String formattedMsg = formatter.format(logMsg);
        try {
            Files.writeString(
                    Path.of(fileName),
                    String.format("%s\n", formattedMsg),
                    java.nio.file.StandardOpenOption.CREATE,
                    java.nio.file.StandardOpenOption.APPEND
            );
        } catch (Exception e) {
            System.out.println("Error occurred while trying to append log to the log file");
        }

    }

}
