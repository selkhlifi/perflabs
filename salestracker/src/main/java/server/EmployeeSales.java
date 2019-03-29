package server;

import java.math.*;

public class EmployeeSales {
    private Employee employee;
    private PointOfSale pos;
    private int nbrOfSales;
    private BigDecimal profit;

    public EmployeeSales(Employee employee, PointOfSale pos, int nbrOfSales, BigDecimal profit) {
        this.employee = employee;
        this.pos = pos;
        this.nbrOfSales = nbrOfSales;
        this.profit = profit;
    }

    public BigDecimal getProfit() {
        return profit;
    }
}
