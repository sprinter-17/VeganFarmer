package market;

public class Supplier {
    private final long costPerItem;

    private long supply = 0;

    public Supplier(long costPerItem) {
        this.costPerItem = costPerItem;
    }

    public double getExpectedMargin(long expectedPrice) {
        return 1.0 * (expectedPrice - costPerItem) / costPerItem;
    }

    public void setSupply(long supply) {
        this.supply = supply;
    }

    public long getSupply() {
        return supply;
    }

    public boolean accept(long price) {
        return price > costPerItem;
    }

    public void supply() {
        supply--;
    }
}
