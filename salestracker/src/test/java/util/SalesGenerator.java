package util;

import java.io.*;
import java.time.*;
import java.util.*;

public class SalesGenerator {

    public static void main(String[] args) throws IOException {

        FileWriter fileWriter = new FileWriter("src/main/resources/all_sales.csv");

        PrintWriter printWriter = new PrintWriter(fileWriter);

        int total = 0;
        LocalDate startDate = LocalDate.of(2010, 01, 01);
        final LocalDate endDate = LocalDate.of(2019, 01, 01);
        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() != DayOfWeek.SUNDAY && date.getDayOfWeek() != DayOfWeek.SATURDAY) {
                for (int pos = 1; pos <= 30; pos++) {
                    for (int emp = 1; pos <= 30; pos++) {
                        // pos;uid(emp.pos);random(0 20);random(0.00 1000,00);date
                        int nbrOfSales = randomNbrOfSales();
                        double profit = randomProfit();
                        String line = String.format("%s\t;emp-%s-%s\t;%s\t;%s", pos, pos, emp, nbrOfSales, profit);
                        printWriter.println(line);
                        total ++;

                    }

                }
            }

        }

        System.out.println(total);
        printWriter.close();

    }

    private static double randomProfit() {
        Random rand = new Random();
        int part1 = rand.nextInt(10) * rand.nextInt(10) * 10;
        int part2 = rand.nextInt(10);
        return Double.parseDouble(part1 + "." + part2);
    }

    private static int randomNbrOfSales() {
        Random rand = new Random();
        return rand.nextInt(21);
    }
}
