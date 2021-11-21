import models.Discount;
import models.Product;
import models.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validators.ProductValidator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductValidatorTest {
    private ProductValidator pv;
    private Product p;

    @BeforeEach
    void setUp() {
        pv= new ProductValidator();
    }

    @Test
    void validateCorectValue() {
        //given
     p = new Product("p0", new BigDecimal(300), 0);
        //when
        ValidationResult result = pv.validate(p);
        //then
        assertTrue(result.getStatus() == ValidationResult.Status.PASS);
    }
    @Test
    void validateZeroValue() {
        //given
        p = new Product("p0", new BigDecimal(0), 0);
        //when
        ValidationResult result = pv.validate(p);
        //then
        assertSame(result.getStatus(), ValidationResult.Status.ERROR);
        assertEquals("Given price is less or equal to 0", result.getReason());

    }

    @Test
    void validateMinusValue() {
        //given
        p = new Product("p0", new BigDecimal(-125), 0);
        //when
        ValidationResult result = pv.validate(p);
        //then
        assertSame(result.getStatus(), ValidationResult.Status.ERROR);
        assertEquals("Given price is less or equal to 0", result.getReason());
    }
}