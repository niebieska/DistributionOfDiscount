package repositories;

import models.Product;

import java.math.BigDecimal;

public interface IRepository {

    void addProduct(Product product);
    void removeProduct(Product product);
    int countProducts();
    BigDecimal sumProductPrices();
}
