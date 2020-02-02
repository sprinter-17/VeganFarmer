package market;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConsumerTest {
    private Consumer consumer;

    @BeforeEach
    void setup() {
        consumer = new Consumer(100);
    }

    @Test
    void testExpectedDemand() {
        assertThat(consumer.getExpectedDemand(50)).isEqualTo(0.5);
        assertThat(consumer.getExpectedDemand(100)).isEqualTo(0.0);
        assertThat(consumer.getExpectedDemand(150)).isEqualTo(-0.5);
    }

    @Test
    void testOffer() {
        consumer.setDemand(50);
        assertThat(consumer.getOffer()).isEqualTo(100);
        consumer.consume();
        assertThat(consumer.getOffer()).isEqualTo(98);
    }
}