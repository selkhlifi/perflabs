package server;

import java.math.*;
import java.util.*;
import java.util.concurrent.*;

import static java.util.stream.Collectors.toList;

public class SalesTracker {

    private static Map<PointOfSale, DailySalesSummary> cache = new ConcurrentHashMap<>();

    public static List<DailySalesSummary> dailySales() {
        final List<PointOfSale> allPointOfSales = SalesRepository.findAllPointsOfSale();
        return allPointOfSales.stream().map(SalesTracker::dailySales).collect(toList());
    }

    private static DailySalesSummary dailySales(final PointOfSale pos) {
        DailySalesSummary dailySalesSummary = cache.get(pos);
        if (dailySalesSummary == null) {
            dailySalesSummary = SalesRepository.retrieveDailySalesSummary(pos).orElseGet(null);
            cache.put(pos, dailySalesSummary);
        }

        return dailySalesSummary;
    }

    public static BigDecimal overallProfits() {
        return SalesRepository.retrieveOverallSales().stream()
                        .map(EmployeeSales::getProfit)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
