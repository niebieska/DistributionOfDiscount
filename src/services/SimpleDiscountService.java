package services;

import models.Discount;
import models.Product;
import models.ValidationException;
import repositories.ProductRepository;
import validators.DiscountValidator;
import validators.ProductValidator;

import java.math.BigDecimal;
import java.util.Set;

public class SimpleDiscountService implements IDiscountService {


    private ProductRepository productRepository;
    private Discount discount;
    private ProductValidator productValidator;
    private DiscountValidator discountValidator;

    public SimpleDiscountService() {
        this.productValidator = new ProductValidator();
        this.discountValidator = new DiscountValidator();
    }

    public SimpleDiscountService(ProductRepository pr, Discount d) throws ValidationException {
        this.productRepository = pr;
        this.discount = d;
        setProducts(productRepository);
        setDiscount(discount);
    }

    private BigDecimal calculateDiscount(Product p, Discount d) {
        return null;
    }

    @Override
    public void setProducts(ProductRepository pr) throws ValidationException {

    }

    @Override
    public void setDiscount(Discount d) throws ValidationException {

    }

    @Override
    public Set<Product> getDiscountedProducts() {
        return null;
    }
}
