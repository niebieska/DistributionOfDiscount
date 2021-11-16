package validators;

import models.Product;
import models.ValidationResult;

public  class ProductValidator implements IValidator{

    @Override
    public ValidationResult validate(Object o) { // with Product p is superclass error
        return null;
    }
}
