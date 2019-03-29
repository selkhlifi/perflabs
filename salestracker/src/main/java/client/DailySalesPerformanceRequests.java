package client;

import server.*;

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
            SalesTracker.dailySales().forEach(salesSummary -> {
                System.out.println(salesSummary);
            });
        }
    }
}
