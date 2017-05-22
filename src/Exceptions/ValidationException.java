package Exceptions;

/**
 * Created by USER on 22.5.2017 г..
 */
public class ValidationException extends IllegalArgumentException {
    public ValidationException() {
    }

    public ValidationException(String s) {
        super(s);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }
}
