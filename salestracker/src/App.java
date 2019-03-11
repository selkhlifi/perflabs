import java.util.*;

import static java.util.stream.Collectors.toList;

public class App {

    private static Map<PointOfSale, SalesSummary> cache = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Thread.sleep(1000);
            new Thread(new Client()).start();
        }
    }

    public static List<SalesSummary> trackSales() {
        final List<PointOfSale> allPointOfSales = PointOfSaleRepository.findAll();
        return allPointOfSales.stream().map(App::salesSummaryOf).collect(toList());
    }

    private static SalesSummary salesSummaryOf(final PointOfSale pos) {
        SalesSummary salesSummary = cache.get(pos);
        if (salesSummary == null) {
            salesSummary = PointOfSaleRepository.retrieveSalesSummary(pos).orElseGet(null);
            cache.put(pos, salesSummary);
        }

        return salesSummary;
    }

    static class Client implements Runnable {

        @Override
        public void run() {
            App.trackSales().forEach(salesSummary -> {
                System.out.println(salesSummary);
            });
        }
    }
}
