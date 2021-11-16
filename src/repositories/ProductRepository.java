package repositories;

import models.Product;

import java.util.Set;

public class ProductRepository implements IRepository {
    Set<Product> productSet;

    @Override
    public void addProduct(Product product) {
        productSet.add(product);
    }

    @Override
    public void removeProduct(Product product) {
        productSet.remove(product);
    }
}
