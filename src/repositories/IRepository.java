package repositories;

import models.Product;

public interface IRepository {

    void addProduct(Product product);
    void removeProduct(Product product);
}
