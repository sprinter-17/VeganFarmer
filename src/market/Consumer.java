package market;

import java.util.Random;

public class Consumer {
    private final long valuePerItem;

    private long demand = 0;
    private long consumption = 0;

    public Consumer(long valuePerItem) {
        this.valuePerItem = valuePerItem;
    }

    public double getExpectedDemand(long expectedPrice) {
        return 1.0 * (valuePerItem - expectedPrice) / valuePerItem;
    }

    public void setDemand(long demand) {
        this.demand = demand;
        this.consumption = 0;
    }

    public long getOffer() {
        return Math.round(valuePerItem - 1.0 * valuePerItem * consumption / demand);
    }

    public void consume() {
        consumption++;
    }

}
