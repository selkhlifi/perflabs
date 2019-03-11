import java.math.*;

class SalesSummary {
    private PointOfSale pos;
    private int nbrOfSales;
    private BigDecimal profit;

    SalesSummary(final PointOfSale pos, final int nbrOfSales, final BigDecimal profit) {
        this.pos = pos;
        this.nbrOfSales = nbrOfSales;
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "POS " + pos.getId() + " performed : " + nbrOfSales + " Sales, profit made " + profit;
    }
}
