import java.util.*;
import java.util.concurrent.*;

import static java.util.stream.Collectors.toList;

class SalesTracker {

    private static Map<PointOfSale, SalesSummary> cache = new ConcurrentHashMap<>();

    static List<SalesSummary> track() {
        final List<PointOfSale> allPointOfSales = PointOfSaleRepository.findAll();
        return allPointOfSales.stream().map(SalesTracker::salesSummaryOf).collect(toList());
    }

    private static SalesSummary salesSummaryOf(final PointOfSale pos) {
        SalesSummary salesSummary = cache.get(pos);
        if (salesSummary == null) {
            salesSummary = PointOfSaleRepository.retrieveSalesSummary(pos).orElseGet(null);
            cache.put(pos, salesSummary);
        }

        return salesSummary;
    }
}
