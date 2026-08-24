import appender.LogAppender;
import enums.Appender;
import enums.Formatter;
import enums.LogLevel;
import factory.LogAppenderFactory;
import factory.LogFormatterFactory;
import factory.LogHandlerFactory;
import formatter.LogFormatter;
import handler.LogHandler;

public class LoggingClient {

    public static void main(String[] args) {

        Logger logger = Logger.getInstance();

        // handlers for each logging level
        LogHandler debugLogHandler = LogHandlerFactory.getLogHandler(LogLevel.DEBUG);
        LogHandler infoLogHandler = LogHandlerFactory.getLogHandler(LogLevel.INFO);
        LogHandler warnLogHandler = LogHandlerFactory.getLogHandler(LogLevel.WARN);
        LogHandler errorLogHandler = LogHandlerFactory.getLogHandler(LogLevel.ERROR);
        LogHandler fatalLogHandler = LogHandlerFactory.getLogHandler(LogLevel.FATAL);

        // appenders for each output destination
        LogAppender consoleLogAppender = LogAppenderFactory.getLogAppender(Appender.CONSOLE);
        LogAppender fileLogAppender = LogAppenderFactory.getLogAppender(Appender.FILE);

        // log msg formatters for each type
        LogFormatter textLogFormatter = LogFormatterFactory.getLogFormatter(Formatter.TEXT);
        LogFormatter jsonLogFormatter = LogFormatterFactory.getLogFormatter(Formatter.JSON);

        // setting text formatter for console
        consoleLogAppender.setFormatter(textLogFormatter);

        // setting json formatter for file
        fileLogAppender.setFormatter(jsonLogFormatter);

        // adding handlers to logger
        logger.addHandler(debugLogHandler);
        logger.addHandler(infoLogHandler);
        logger.addHandler(warnLogHandler);
        logger.addHandler(errorLogHandler);
        logger.addHandler(fatalLogHandler);

        // setting console appender for each log level type
        logger.addAppenderForHandler(consoleLogAppender, LogLevel.DEBUG);
        logger.addAppenderForHandler(consoleLogAppender, LogLevel.INFO);
        logger.addAppenderForHandler(consoleLogAppender, LogLevel.WARN);
        logger.addAppenderForHandler(consoleLogAppender, LogLevel.ERROR);
        logger.addAppenderForHandler(consoleLogAppender, LogLevel.FATAL);

        // setting file appender for only 'error' and 'fatal' log levels
        logger.addAppenderForHandler(fileLogAppender, LogLevel.ERROR);
        logger.addAppenderForHandler(fileLogAppender, LogLevel.FATAL);

        // DEBUG level log msg
        logger.debug("This is a debug level log msg");

        // INFO level log msg
        logger.info("This is a info level log msg");

        // WARN level log msg
        logger.warn("This is a warn level log msg");

        // ERROR level log msg
        logger.error("This is a error level log msg");

        // FATAL level log msg
        logger.fatal("This is a fatal level log msg");

    }

}
