package validators;

import models.Product;
import models.ValidationResult;

import java.math.BigDecimal;

public class ProductValidator implements IValidator<Product> {

    @Override
    public ValidationResult validate(Product p) {
        ValidationResult validationResult = new ValidationResult(ValidationResult.Status.PASS);
        BigDecimal productPrice = p.getPrice();

        if (productPrice.signum() <= 0) {
            validationResult.setReason("Price is less or equal to 0");
            validationResult.setStatus(ValidationResult.Status.ERROR);
        }
        return validationResult;
    }
}
