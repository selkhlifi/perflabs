package client;

import server.*;
import util.*;

import java.math.*;

public class OverallSalesPerformanceRequests {

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(10000);

        for (int i = 0; i < 200; i ++) {
            final BigDecimal result = LatencyMonitor.executeAndMonitor(SalesTracker::overallProfits);
            System.out.println(">>> Overall benefits : " + result);
        }

        Thread.sleep(10000);
    }
}
