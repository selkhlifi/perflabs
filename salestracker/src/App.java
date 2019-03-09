import java.util.*;

public class App {

    private static Map<PointOfService, SalesSummary> cache = new HashMap<>();

    public static void main(String[] args) {
        final List<PointOfService> allPointOfServices = PointOfServiceRepository.findAll();
        allPointOfServices.stream().map(App::salesSummaryOf).forEach(System.out::println);
    }

    private static SalesSummary salesSummaryOf(final PointOfService pos) {
        SalesSummary salesSummary = cache.get(pos);
        if (salesSummary == null) {
            salesSummary = PointOfServiceRepository.retrieveSalesSummary(pos).orElseGet(null);
            cache.put(pos, salesSummary);
        }

        return salesSummary;
    }
}
