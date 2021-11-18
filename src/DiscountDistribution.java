import models.Product;
import repositories.IRepository;
import repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.Scanner;

public class DiscountDistribution {
    public static void main(String[] args) {

        IRepository pr = new ProductRepository();

        // wczytywanie def produktów nazwa, cena i nadanie nr
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        System.out.println("Podaj liczbę produktów!");
        count =scanner.nextInt();
        for(int i=1; i<=count; i++)
        {
            System.out.println("Podaj nazwę produktu" + i);
            String name=scanner.next();
            System.out.println("Podaj cene produktu" + i);

            BigDecimal price  =scanner.nextBigDecimal();
            Product p= new Product(name,price, i);
            pr.addProduct(p);

        }



    }

}
