import java.io.*;
import java.math.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class PointOfServiceRepository {

    private static final String SEPARATOR = ";";

    public static List<PointOfService> findAll() {
        final List<PointOfService> result = new LinkedList<>();
        result.add(new PointOfService(1));
        result.add(new PointOfService(2));
        result.add(new PointOfService(3));
        result.add(new PointOfService(4));
        result.add(new PointOfService(5));
        result.add(new PointOfService(6));
        result.add(new PointOfService(7));
        result.add(new PointOfService(8));
        result.add(new PointOfService(9));
        result.add(new PointOfService(10));
        return result;
    }

    public static Optional<SalesSummary> retrieveSalesSummary(final PointOfService pos) {
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
