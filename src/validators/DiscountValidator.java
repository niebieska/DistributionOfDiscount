package validators;

import models.Discount;
import models.ValidationResult;

import java.math.BigDecimal;

public class DiscountValidator implements IValidator<Discount>{

    @Override
    public ValidationResult validate(Discount d) {
        ValidationResult validationResult = new ValidationResult(ValidationResult.Status.PASS);
        BigDecimal discountAmount = d.getAmount();

        if (discountAmount.signum() <= 0) {
            validationResult.setReason("Discount is less or equal to 0");
            validationResult.setStatus(ValidationResult.Status.ERROR);
        }
        return validationResult;

    }
}
