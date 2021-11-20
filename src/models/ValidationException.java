package models;

public class ValidationException extends RuntimeException {
    public ValidationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
