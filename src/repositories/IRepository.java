package repositories;

import models.Product;

import java.math.BigDecimal;
import java.util.Set;

public interface IRepository {

    void addProduct(Product product);
    void removeProduct(Product product);
    int countProducts();
    BigDecimal sumProductPrices();
    public Set<Product> getProductsList();
}
