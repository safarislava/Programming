package Exceptions;

public class NullOpinionException extends RuntimeException {
    public NullOpinionException() {}

    public NullOpinionException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Exception: " + super.getMessage();
    }
}
