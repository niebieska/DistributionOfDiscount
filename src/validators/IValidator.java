package validators;

import models.ValidationResult;

public class IValidator<T> {

public ValidationResult validate(T t);
}
