package services;

import models.Discount;
import models.Product;
import models.ValidationException;
import models.ValidationResult;
import repositories.ProductRepository;
import validators.DiscountValidator;
import validators.ProductValidator;

import java.math.BigDecimal;
import java.util.HashSet;
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
        this.productValidator = new ProductValidator();
        this.discountValidator = new DiscountValidator();
        this.productRepository = pr;
        this.discount = d;
        setProducts(productRepository);
        setDiscount(discount);
        //calculateDiscountFactor(pr,d);
    }

    private BigDecimal calculateDiscount(Product p, Discount d) {

        BigDecimal discount = new BigDecimal(0);
        discount = p.getPrice().multiply(d.getFactor());
        return discount;
    }

    private void calculateDiscountFactor(ProductRepository pr, Discount d) {
        BigDecimal pricesSum = pr.sumProductPrices();
        BigDecimal factor;
        factor = d.getAmount().divide(pricesSum);
        d.setFactor(factor);
    }

    @Override
    public void setProducts(ProductRepository pr) throws ValidationException {
        ValidationResult validationResult;
        for (Product p : pr.getProductsList()) {
            validationResult = productValidator.validate(p);
            if (validationResult.getStatus() == ValidationResult.Status.ERROR)
                throw new ValidationException(validationResult.getReason());
        }
    }

    @Override
    public void setDiscount(Discount d) throws ValidationException {
        ValidationResult validationResult;
        validationResult = discountValidator.validate(d);
        if (validationResult.getStatus() == ValidationResult.Status.ERROR)
            throw new ValidationException(validationResult.getReason());
    }

    @Override
    public Set<Product> getDiscountedProducts(ProductRepository pr, Discount d) {
        Set<Product> discountedProducts = new HashSet<>();
        calculateDiscountFactor(productRepository, discount);
        BigDecimal rest = d.getAmount();
        Product lastProduct = null;
        for (Product p : pr.getProductsList()) {
            BigDecimal discount = calculateDiscount(p, d);
            rest.subtract(discount);
            Product product = new Product(p.getName(), p.getPrice().subtract(discount), p.getNumber());
            discountedProducts.add(product);
            if (p.getNumber() == pr.getProductsList().size()) {
                lastProduct = p;
            }
        }
        if (rest.signum() != 0) {
            discountedProducts.remove(lastProduct);
            lastProduct.getPrice().subtract(rest);
            discountedProducts.add(lastProduct);
        }
        return discountedProducts;
    }
}
