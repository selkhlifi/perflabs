package client;

import server.*;

import java.math.*;
import java.time.*;

public class OverallSalesPerformanceRequests {

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(10000);

        for (int i = 0; i < 200; i ++) {
            Instant start = Instant.now();
            BigDecimal result = SalesTracker.overallProfits();
            Duration interval = Duration.between(start, Instant.now());
            System.out.println("Result : " + result);
            System.out.println("Execution time in seconds: " + interval.getNano());
        }

        Thread.sleep(10000);
    }
}
