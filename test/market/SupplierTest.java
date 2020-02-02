package market;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SupplierTest {
    private Supplier supplier;

    @BeforeEach
    void setup() {
        supplier = new Supplier(100);
    }

    @Test
    void testMargin() {
        assertThat(supplier.getExpectedMargin(50)).isEqualTo(-0.5);
        assertThat(supplier.getExpectedMargin(100)).isEqualTo(0.0);
        assertThat(supplier.getExpectedMargin(150)).isEqualTo(0.5);
    }

    @Test
    void testSupply() {
        assertThat(supplier.getSupply()).isEqualTo(0);
        supplier.setSupply(50L);
        assertThat(supplier.getSupply()).isEqualTo(50L);
    }
}