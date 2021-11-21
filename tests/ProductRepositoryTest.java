import models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class ProductRepositoryTest {
    private ProductRepository pr;
    private Product p1, p2;

    @BeforeEach
    void setUp() {
        pr = new ProductRepository();
        p1 = new Product("p1", new BigDecimal(500), 1);
        p2 = new Product("p2", new BigDecimal(1500), 2);
    }

    @Test
    void addProduct() {
        //given
        Product p = new Product("p0", new BigDecimal(300), 0);
        //when
        pr.addProduct(p);
        //then
        assertTrue(pr.getProductsList().contains(p));

    }

    @Test
    void removeProduct() {
        //given
        pr.addProduct(p1);
        pr.addProduct(p2);
        //when
        pr.removeProduct(p1);
        //then
        assertFalse(pr.getProductsList().contains(p1));
    }

    @Test
    void countProducts() {
        //Given
        pr.addProduct(p1);
        pr.addProduct(p2);
        //when
        int count = pr.countProducts();
        //then
        assertEquals(2, count);
    }

    @Test
    void sumProductPrices() {
        //Given
        pr.addProduct(p1);
        pr.addProduct(p2);
        //when
        BigDecimal sum = pr.sumProductPrices();
        //then
        assertEquals(new BigDecimal(2000), sum);
    }


    @Test
    void getProductsList() {
        //Given
        pr.addProduct(p1);
        pr.addProduct(p2);
        //when
        Set<Product> productSet = pr.getProductsList();
        //then
        assertTrue(productSet.contains(p1) && productSet.contains(p2) && productSet.size() == 2);

    }
}