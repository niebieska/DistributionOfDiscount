import models.Discount;
import models.Product;
import models.ValidationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import repositories.ProductRepository;
import services.SimpleDiscountService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SimpleDiscountServiceTest {
    private SimpleDiscountService sds;
    private ProductRepository pr;
    private Product p1,p2,p3;
    private Discount d;

    @BeforeEach
    void setUp() {
        pr = new ProductRepository();
        sds = new SimpleDiscountService();
    }
    @Before
    void setUp_SetProductCorrectProduct() {
        p1=new Product("P1", new BigDecimal(500), 1);
        p2 = new Product("P2", new BigDecimal(1500), 2);
        pr.addProduct(p1);
        pr.addProduct(p2);
    }

    @Test()
    void setProductsCorrectProduct() {
        //given
        setUp_SetProductCorrectProduct();
        //when
        sds.setProducts(pr);
        //then
        //nothing happened
    }

    @Before
    void setUp_SetProductWrongProduct() {
        p1=new Product("P1", new BigDecimal(-500), 1);
        p2 = new Product("P2", new BigDecimal(1500), 2);
        pr.addProduct(p1);
        pr.addProduct(p2);
    }
    @Test
    void setProductsWrongProduct() {
        //given
        setUp_SetProductWrongProduct();
        String result = "Given price is less or equal to 0";
        //when
        try {sds.setProducts(pr);}
        catch (ValidationException e)
        {   //then
            assertEquals(result,e.getMessage());
        }
    }

    @Before
    void setUp_SetDiscountCorrect() {
        d = new Discount( new BigDecimal(100));
    }
    @Test
    void setDiscountCorrect() {
        //given
        setUp_SetDiscountCorrect();
        //when
        sds.setDiscount(d);
        //then
        // //nothing happened
    }

    @Before
    void setUp_SetDiscountWrong() {
        d = new Discount( new BigDecimal(-154));
    }
    @Test
    void setDiscountWrong() {
        //given
        setUp_SetDiscountWrong();
        String result = "Given discount is less or equal to 0";
        //when
        try {
            sds.setDiscount(d);
        } catch (ValidationException e) {
            //then
            assertEquals(result, e.getMessage());
        }
    }

    // to imply 
    @Test
    void getDiscountedProducts() {
    }
}