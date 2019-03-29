package server;

import java.io.*;
import java.math.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class SalesRepository {

    private static final String SEPARATOR = ";";
    private static final String SALES_FILES_PATH = "src/main/resources/daily_sales.csv";
    private static final String SALES2_FILES_PATH = "src/main/resources/all_sales.csv";

    public static List<PointOfSale> findAllPointsOfSale() {
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

    public static Optional<DailySalesSummary> retrieveDailySalesSummary(final PointOfSale pos) {
        final Path salesFilePath = Paths.get(SALES_FILES_PATH);
        try (Stream<String> input = Files.lines(salesFilePath)) {
            return input.filter(line -> pos.getId() == Long.parseLong(line.split(SEPARATOR)[0])).map(line -> {
                final String[] lineSplit = line.split(SEPARATOR);
                        final int nbrOfSales = Integer.parseInt(lineSplit[1]);
                        final BigDecimal profit = new BigDecimal(lineSplit[2]);
                        return new DailySalesSummary(pos, nbrOfSales, profit);
                    }).findFirst();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<EmployeeSales> retrieveOverallSales() {
        final List<EmployeeSales> result = new ArrayList<>();
        final Path salesFilePath = Paths.get(SALES2_FILES_PATH);
        try (Stream<String> input = Files.lines(salesFilePath)) {
            input.forEach(line -> {
                final String[] lineSplit = line.split(";");
                final PointOfSale pos = new PointOfSale(Long.parseLong(lineSplit[0].trim()));
                final Employee emp = new Employee(lineSplit[1].trim());
                final int nbrOfSales = Integer.parseInt(lineSplit[2].trim());
                final BigDecimal profit = new BigDecimal(lineSplit[3].trim());
                final EmployeeSales empSales = new EmployeeSales(emp, pos, nbrOfSales, profit);
                result.add(empSales);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
