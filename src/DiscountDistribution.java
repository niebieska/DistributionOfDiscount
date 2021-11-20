import models.Discount;
import models.Product;
import models.ValidationException;
import repositories.IRepository;
import repositories.ProductRepository;
import services.IDiscountService;
import services.SimpleDiscountService;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;

public class DiscountDistribution {
    public static void main(String[] args) {

        ProductRepository pr = new ProductRepository();

        Product p1 = new Product("P1", BigDecimal.ZERO, 1);
        Product p2 = new Product("P1", new BigDecimal(-1), 2);
        Discount d = new Discount(BigDecimal.ZERO);
        pr.addProduct(p1);
        IDiscountService ds = new SimpleDiscountService();
        try {
            ds.setProducts(pr);

        } catch (ValidationException e) {
            System.out.println(e);
        }

        try {
            ds.setDiscount(d);

        } catch (ValidationException e) {
            System.out.println(e);

        }
        //Set<Product> discountedProducts = ds.getDiscountedProducts(pr,d);


    }


}


