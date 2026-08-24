package strategy.appender;

import entity.LogMessage;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogAppender extends LogAppender {

    private FileWriter fileWriter;

    public FileLogAppender(String filePath) {

        try {
            fileWriter = new FileWriter(filePath, true);
        } catch (IOException e) {
            System.out.println("Failed to create writer for file logs, exception: " + e.getMessage());
        }

    }

    @Override
    public void append(LogMessage logMsg) {

        String formatterMsg = logFormatter.format(logMsg);
        try {
            fileWriter.write(formatterMsg);
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Failed to write logs to file, exception: " + e.getMessage());
        }

    }

    @Override
    public void close() {

        try {
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to close logs file, exception: " + e.getMessage());
        }

    }

}
