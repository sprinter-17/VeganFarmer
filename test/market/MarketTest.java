package market;

import model.Controls;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.mockito.Mockito.mock;

class MarketTest {
    private Random random;
    private Controls controls;
    private Market market;

    @BeforeEach
    void setup() {
        random = new Random(5L);
        controls = new Controls(random);
        market = new Market(controls);
    }

    @Test
    void testTransact() {
        market.transact();
        market.transact();
        market.transact();
        market.transact();
        market.transact();
        market.transact();
        market.transact();
        market.transact();
    }

}