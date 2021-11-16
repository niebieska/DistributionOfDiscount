package validators;

import models.ValidationResult;

public interface IValidator<T> {
    public ValidationResult validate(T t);

}
