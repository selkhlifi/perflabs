import java.io.*;
import java.math.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class PointOfSaleRepository {

    private static final String SEPARATOR = ";";

    public static List<PointOfSale> findAll() {
        final List<PointOfSale> result = new LinkedList<>();
        result.add(new PointOfSale(1));
        result.add(new PointOfSale(2));
        result.add(new PointOfSale(3));
        result.add(new PointOfSale(4));
        result.add(new PointOfSale(5));
        result.add(new PointOfSale(6));
        result.add(new PointOfSale(7));
        result.add(new PointOfSale(8));
        result.add(new PointOfSale(9));
        result.add(new PointOfSale(10));
        return result;
    }

    public static Optional<SalesSummary> retrieveSalesSummary(final PointOfSale pos) {
        final Path salesFilePath = Paths.get("sales.csv");
        try (Stream<String> input = Files.lines(salesFilePath)) {
            return input.filter(line -> pos.getId() == Long.parseLong(line.split(SEPARATOR)[0])).map(line -> {
                final String[] lineSplit = line.split(SEPARATOR);
                        final int nbrOfSales = Integer.parseInt(lineSplit[1]);
                        final BigDecimal profit = new BigDecimal(lineSplit[2]);
                        return new SalesSummary(pos, nbrOfSales, profit);
                    }).findFirst();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
