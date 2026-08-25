import entity.LogMessage;
import strategy.appender.LogAppender;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsyncLogProcessor {

    private static AsyncLogProcessor INSTANCE;
    private final ExecutorService executor;

    private AsyncLogProcessor() {
        this.executor = Executors.newSingleThreadExecutor(runnable -> {
            Thread thread = new Thread(runnable, "AsyncLogProcessor");
            thread.setDaemon(true);
            return thread;
        });
    }

    public static AsyncLogProcessor getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new AsyncLogProcessor();
        }
        return INSTANCE;
    }

    public void process(LogMessage logMsg, List<LogAppender> appenders) {

        if (executor.isShutdown()) {
            System.out.println("Executor service is shut down !!! Cannot log the messages");
            return;
        }

        // submit task to executor
        // so that it can process it immediately
        // or if worker thread is busy
        // then add task in the work queue
        executor.submit(() -> {
            for (LogAppender appender: appenders) {
                appender.append(logMsg);
            }
        });

    }

    public void shutdown() {

        // stop receiving any new task
        executor.shutdown();

        try {
            if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                System.out.println("Executor did not get terminated within the time specified");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }

    }

}
