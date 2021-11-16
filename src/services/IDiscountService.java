package services;

import models.Discount;
import models.Product;
import models.ValidationException;
import repositories.ProductRepository;

import java.util.Set;

public interface IDiscountService {


    public void setProducts(ProductRepository pr) throws ValidationException;

    public void setDiscount(Discount d) throws ValidationException;

    public Set<Product> getDiscountedProducts();
}
