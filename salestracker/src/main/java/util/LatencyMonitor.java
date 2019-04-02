package util;

import java.time.*;
import java.util.concurrent.*;
import java.util.function.*;

public class LatencyMonitor {

    public static <T> T executeAndMonitor(Supplier<T> process) {
        Instant start = Instant.now();

        final T result = process.get();

        Duration interval = Duration.between(start, Instant.now());
        System.out.println("Execution time in ms : "
                           + TimeUnit.MILLISECONDS.convert(interval.getNano(), TimeUnit.NANOSECONDS));
        return result;
    }

}
