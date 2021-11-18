package models;

import java.math.BigDecimal;

public class Product {

    private String name;
    private BigDecimal price;
    private int number;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public Product(String name, BigDecimal price, int number) {
        this.name = name;
        this.price = price;
        this.number = number;
    }


}
