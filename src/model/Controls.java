package model;

import market.Consumer;
import market.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Controls {
    private final Random random;

    private int supplierCount = 100;
    private final Distribution supply = new Distribution(100, 10);
    private final Distribution cost = new Distribution(100, 10);

    private int consumerCount = 1000;
    private final Distribution demand = new Distribution(20, 2);
    private final Distribution value = new Distribution(200, 20);

    public Controls(Random random) {
        this.random = random;
    }

    public Stream<Supplier> generateSuppliers() {
        return LongStream.range(0, supplierCount)
                .mapToObj(n -> new Supplier(cost.generate()));
    }

    public Stream<Consumer> generateConsumers() {
        return LongStream.range(0, consumerCount)
                .mapToObj(n -> new Consumer(value.generate()));
    }

    public long getStartingPrice() {
        return (cost.average + value.average) / 2;
    }

    public long generateSupply(double expectedMargin) {
        return supply.generate(expectedMargin);
    }

    public long generateDemand(double expectedMargin) {
        return demand.generate(expectedMargin);
    }

    private class Distribution {
        private final long average;
        private final long standardDeviation;

        private Distribution(long average, long standardDeviation) {
            this.average = average;
            this.standardDeviation = standardDeviation;
        }

        public long generate() {
            return generate(1.0);
        }

        public long generate(double scale) {
            double value = average * scale + random.nextGaussian() * standardDeviation;
            return Math.round(Math.max(0.0, value));
        }
    }
}
