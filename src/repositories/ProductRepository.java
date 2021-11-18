package repositories;

import models.Product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProductRepository implements IRepository {
    private Set<Product> productSet;

    public ProductRepository()
    {
        this.productSet = new HashSet<>();
    }

    @Override
    public void addProduct(Product product) {
        productSet.add(product);
    }

    @Override
    public void removeProduct(Product product) {
        productSet.remove(product);
    }

    @Override
    public int countProducts() {
        return productSet.size();
    }

    @Override
    public BigDecimal sumProductPrices() {
        BigDecimal sum = new BigDecimal(0);
        for( Product p: productSet)
        {
            sum.add(p.getPrice());
        }
        return sum;
    }

    @Override
    public Set<Product> getProductsList() {
        return productSet ;
    }
}
