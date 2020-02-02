package model;

import market.Market;

import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Random;

public class Simulate {
    private final Controls controls;

    public Simulate(Controls controls) {
        this.controls = controls;
    }

    public long simulate(int cycles) {
            Market market = new Market(controls);

            // stabilise price
            run(market, cycles);

            // control
            long control = run(market, cycles);

            // experiment
            market.removeConsumer();
            long experiment = run(market, cycles);

            return control - experiment;
    }

    private long run(Market market, int cyles) {
        long totalSupply = 0;
        for (int i = 0; i < cyles; i++) {
            totalSupply += market.transact();
        }
        return totalSupply;
    }

    public static void main(String[] args) {
        Controls controls = new Controls(new Random());
        Simulate simulate = new Simulate(controls);
        LongSummaryStatistics stats = new LongSummaryStatistics();
        for (int i = 0; i < 1000; i++) {
            stats.accept(simulate.simulate(20));
            System.out.println("#" + stats.getCount() + " avg production diff = " + stats.getAverage());
        }
    }
}
