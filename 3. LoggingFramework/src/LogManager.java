import strategy.appender.LogAppender;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogManager {

    private static LogManager INSTANCE; // SINGLETON
    private final Map<String, Logger> loggers;
    private final AsyncLogProcessor processor;
    private final Logger rootLogger;

    private LogManager() {
        loggers = new ConcurrentHashMap<>();
        processor = AsyncLogProcessor.getInstance();
        rootLogger = new Logger("root", null);
    }

    public static LogManager getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new LogManager();
        }
        return INSTANCE;
    }

    public Logger getLogger(String name) {

        if (loggers.containsKey(name)) {
            return loggers.get(name);
        }
        return createLogger(name);

    }

    private Logger createLogger(String loggerName) {

        int lastIndex = loggerName.lastIndexOf('.');
        Logger parentLogger;
        if (lastIndex == -1) {
            parentLogger = rootLogger;
        } else {
            parentLogger = getLogger(loggerName.substring(0, lastIndex));
        }
        Logger logger = new Logger(loggerName, parentLogger);
        loggers.put(loggerName, logger);
        return logger;

    }

    public AsyncLogProcessor getProcessor() {
        return processor;
    }

    public void shutdown() {

        // gracefully close the log processor
        processor.shutdown();

        // then close all appenders
        // so that acquired resources are released
        loggers.values().stream()
                .flatMap(logger -> logger.getAppenders().stream())
                .distinct()
                .forEach(LogAppender::close);
        System.out.println("Logging framework shut down gracefully");

    }

}
