package client;

import server.*;
import util.*;

import java.math.*;
import java.util.concurrent.*;

public class DailySalesPerformanceRequests {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 100000; i++) {
            Runnable trackingRequest = new Request();
            executor.execute(trackingRequest);
        }

        executor.shutdown();
    }

    static class Request implements Runnable {

        @Override
        public void run() {

            final BigDecimal result =
                    LatencyMonitor.executeAndMonitor(() -> SalesTracker.dailySales()
                            .stream()
                            .map(DailySalesSummary::getProfit)
                            .reduce(BigDecimal.ZERO, BigDecimal::add));
            System.out.println(">>> Daily benefits : " + result);
        }
    }
}
