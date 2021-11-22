import models.Discount;
import models.Product;
import models.ValidationException;
import repositories.IRepository;
import repositories.ProductRepository;
import services.IDiscountService;
import services.SimpleDiscountService;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class DiscountDistribution {

    private int getInput_ProductCount() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        boolean again = true;
        while (again) {
            try {
                System.out.println("Give number of products (min 2, max 5):");
                count = scanner.nextInt();
                if (count < 2 || count > 5) {
                    System.out.println("Given number is out of range [2,5]!\n");
                    again = true;
                } else {
                    again = false;
                }
            } catch (Exception ex) {
                System.out.println("Attention! Given input is not a number!");
                scanner.next();
            }
        }
        return count;
    }

    private BigDecimal getInput_DiscountAmount() {
        Scanner scanner = new Scanner(System.in);
        boolean again = true;
        BigDecimal amount = new BigDecimal(0);

        while (again) {
            try {
                System.out.println("Give a discount amount:");
                amount = scanner.nextBigDecimal();
                again = false;
            } catch (InputMismatchException ex) {
                System.out.println("Attention! Given input is not a number!");
                scanner.next();
            }
        }
        return amount;
    }

    private String getInput_ProductName() {
        String name = "";
        Scanner scanner = new Scanner(System.in);
        boolean again = true;
        while (again) {
            System.out.println("Give product name!(Name can contains only letters & numbers :");
            try {
                name = scanner.next("[a-zA-Z0-9]+");
                again = false;
            } catch (InputMismatchException ex) {
                System.out.println("Attention! Given input contains forbidden char!");
                scanner.next();
            }
        }
        return name;
    }

    private BigDecimal getInput_ProductPrice(int i) {
        BigDecimal productPrice = new BigDecimal(0);
        Scanner scanner = new Scanner(System.in);
        boolean again = true;

        while (again) {
            try {
                System.out.println("Give product" + i + "price:");
                productPrice = scanner.nextBigDecimal();
                again = false;
            } catch (Exception e) {
                System.out.println("Attention! Given input is not a number.");
                scanner.next();
            }
        }
        return productPrice;
    }

    private ProductRepository getAllUserProducts(int count) {
        DiscountDistribution distribution = new DiscountDistribution();
        ProductRepository pr = new ProductRepository();
        for (int i = 1; i <= count; i++) {
            String name = distribution.getInput_ProductName();
            BigDecimal price = distribution.getInput_ProductPrice(i);
            Product p = new Product(name, price, i);
            pr.addProduct(p);
        }
        return pr;
    }


    public static void main(String[] args) {

        ProductRepository pr = new ProductRepository();
        DiscountDistribution distribution = new DiscountDistribution();

        // Reading product definitions from user

        int count = distribution.getInput_ProductCount();
        pr = distribution.getAllUserProducts(count);


        //Reading discount amount
        BigDecimal discountAmount = distribution.getInput_DiscountAmount();
        Discount d = new Discount(discountAmount);

        //Service creation and calculations
        IDiscountService ds = new SimpleDiscountService();

        try {
            ds.setProducts(pr);

        } catch (ValidationException e) {

            System.out.println(e.getMessage());
            pr = new ProductRepository();
            // Reading product definitions from user again
            count = distribution.getInput_ProductCount();
            pr = distribution.getAllUserProducts(count);
        }

        try {
            ds.setDiscount(d);

        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            //Reading discount amount
            discountAmount = distribution.getInput_DiscountAmount();
            d = new Discount(discountAmount);

        } finally {
            ds = new SimpleDiscountService(pr, d);
        }

        Set<Product> discountedProducts = ds.getDiscountedProducts(pr, d);
        System.out.println("Product List:");
        for (Product p : discountedProducts) {
            System.out.println("nr: " + p.getNumber() + "\t name: " + p.getName() + "\t discounted price :" + p.getPrice());
        }

    }

}

