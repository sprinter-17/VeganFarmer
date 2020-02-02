package market;

import model.Controls;

import java.util.*;
import java.util.stream.LongStream;

public class Market {
    private final Controls controls;
    private final List<Supplier> suppliers = new ArrayList<>();
    private final List<Consumer> consumers = new ArrayList<>();

    private long lastPrice;

    public Market(Controls controls) {
        this.controls = controls;
        controls.generateSuppliers().forEach(suppliers::add);
        controls.generateConsumers().forEach(consumers::add);
        lastPrice = controls.getStartingPrice();
    }

    public void removeConsumer() {
        consumers.remove(0);
    }

    public long transact() {
        LongSummaryStatistics stats = new LongSummaryStatistics();
        suppliers.forEach(s -> s.setSupply(controls.generateSupply(s.getExpectedMargin(lastPrice))));
        consumers.forEach(c -> c.setDemand(controls.generateDemand(c.getExpectedDemand(lastPrice))));
        long totalItems = suppliers.stream().mapToLong(Supplier::getSupply).sum();
        boolean transactionOccured = false;
        do {
            transactionOccured = false;
            Collections.shuffle(suppliers);
            for (Supplier supplier: suppliers) {
                Optional<Consumer> customer = consumers.stream().max(Comparator.comparingLong(Consumer::getOffer));
                if (customer.isPresent() && supplier.accept(customer.get().getOffer())) {
                    stats.accept(customer.get().getOffer());
                    supplier.supply();
                    customer.get().consume();
                    transactionOccured = true;
                }
            }
        } while (transactionOccured);
        lastPrice = Math.round(stats.getAverage());
        return totalItems;
    }
}
