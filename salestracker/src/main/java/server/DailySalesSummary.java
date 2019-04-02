package server;

import java.math.*;

public class DailySalesSummary {
    private PointOfSale pos;
    private int nbrOfSales;
    private BigDecimal profit;

    DailySalesSummary(final PointOfSale pos, final int nbrOfSales, final BigDecimal profit) {
        this.pos = pos;
        this.nbrOfSales = nbrOfSales;
        this.profit = profit;
    }

    public BigDecimal getProfit() {
        return this.profit;
    }

    @Override
    public String toString() {
        return "POS " + pos.getId() + " performed : " + nbrOfSales + " Sales, profit made " + profit;
    }
}
