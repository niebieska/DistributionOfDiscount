import models.Discount;
import models.ValidationResult;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validators.DiscountValidator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DiscountValidatorTest {
    private Discount d;
    private DiscountValidator dv;

    @BeforeEach
    void setUp() {
        dv = new DiscountValidator();
    }

    @Test
    void validateCorectValue() {
        //given
        d = new Discount(new BigDecimal(100));
        //when
        ValidationResult result = dv.validate(d);
        //then
        assertTrue(result.getStatus() == ValidationResult.Status.PASS);
    }

    @Test
    void validateZeroValue() {
        //given
        d = new Discount(new BigDecimal(0));
        //when
        ValidationResult result = dv.validate(d);
        //then
        assertSame(result.getStatus(), ValidationResult.Status.ERROR);
        assertEquals("Given discount is less or equal to 0", result.getReason());

    }

    @Test
    void validateMinusValue() {
        //given
        d = new Discount(new BigDecimal(-152));
        //when
        ValidationResult result = dv.validate(d);
        //then
        assertSame(result.getStatus(), ValidationResult.Status.ERROR);
        assertEquals("Given discount is less or equal to 0", result.getReason());
    }
}