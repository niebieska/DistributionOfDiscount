import models.Discount;
import models.Product;
import models.ValidationException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.ProductRepository;
import services.SimpleDiscountService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SimpleDiscountServiceTest {
    private SimpleDiscountService sds;
    private ProductRepository pr;
    private Product p1, p2, p3;
    private Discount d;
    private Map<String, BigDecimal> resultMap;

    @BeforeEach
    void setUp() {
        pr = new ProductRepository();
        sds = new SimpleDiscountService();
    }

    @Before
    public void setUp_SetProductCorrectProduct() {
        p1 = new Product("P1", new BigDecimal(500), 1);
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
    public void setUp_SetProductWrongProduct() {
        p1 = new Product("P1", new BigDecimal(-500), 1);
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
        try {
            sds.setProducts(pr);
        } catch (ValidationException e) {   //then
            assertEquals(result, e.getMessage());
        }
    }

    @Before
    public void setUp_SetDiscountCorrect() {
        d = new Discount(new BigDecimal(100));
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
    public void setUp_SetDiscountWrong() {
        d = new Discount(new BigDecimal(-154));
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

    @Before
    public void setUp_getTwoDiscountedProducts() {
        p1 = new Product("P1", new BigDecimal(500), 1);
        p2 = new Product("P2", new BigDecimal(1500), 2);
        pr.addProduct(p1);
        pr.addProduct(p2);
        d = new Discount(new BigDecimal(100));
        resultMap = new HashMap<>();
        resultMap.put("P1", new BigDecimal(475).setScale(2, BigDecimal.ROUND_HALF_UP));
        resultMap.put("P2", new BigDecimal(1425).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    // to imply
    @Test
    void getTwoDiscountedProducts() {
        //given

        setUp_getTwoDiscountedProducts();
        Set<Product> resultSet = sds.getDiscountedProducts(pr, d);
        for (Product p : resultSet) {
            assertEquals(p.getPrice(), resultMap.get(p.getName()));
        }
        assertEquals(resultSet.size(), 2);

    }


    @Before
    public void setUp_getThreeDiscountedProducts() {
        p1 = new Product("P1", new BigDecimal(100), 1);
        p2 = new Product("P2", new BigDecimal(100), 2);
        p3 = new Product("P3", new BigDecimal(100), 3);
        pr.addProduct(p1);
        pr.addProduct(p2);
        pr.addProduct(p3);
        d = new Discount(new BigDecimal(100));
        resultMap = new HashMap<>();
        resultMap.put("P1", new BigDecimal(67.00).setScale(2, BigDecimal.ROUND_HALF_UP));
        resultMap.put("P2", new BigDecimal(67.00).setScale(2, BigDecimal.ROUND_HALF_UP));
        resultMap.put("P3", new BigDecimal(66.00).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    void getThreeDiscountedProducts() {
        //given
        setUp_getThreeDiscountedProducts();
        //when
        Set<Product> resultSet = sds.getDiscountedProducts(pr, d);
        //then
        for (Product p : resultSet) {
            assertEquals(p.getPrice(), resultMap.get(p.getName()));
        }
        assertEquals(resultSet.size(), 3);

    }

    @Before
    public void setUp_getThreeDiscountedProducts_BigDiscount() {
        p1 = new Product("P1", new BigDecimal(100), 1);
        p2 = new Product("P2", new BigDecimal(100), 2);
        p3 = new Product("P3", new BigDecimal(100), 3);
        pr.addProduct(p1);
        pr.addProduct(p2);
        pr.addProduct(p3);
        d = new Discount(new BigDecimal(200));
        resultMap = new HashMap<>();
        resultMap.put("P1", new BigDecimal(33.00).setScale(2, BigDecimal.ROUND_HALF_UP));
        resultMap.put("P2", new BigDecimal(33.00).setScale(2, BigDecimal.ROUND_HALF_UP));
        resultMap.put("P3", new BigDecimal(34.00).setScale(2, BigDecimal.ROUND_HALF_UP));
    }


    @Test
    void getThreeDiscountedProducts_BigDiscount() {
        //given
        setUp_getThreeDiscountedProducts_BigDiscount();
        //when
        Set<Product> resultSet = sds.getDiscountedProducts(pr, d);
        //then
        for (Product p : resultSet) {
            assertEquals(p.getPrice(), resultMap.get(p.getName()));
        }
        assertEquals(resultSet.size(), 3);

    }
}