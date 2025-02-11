package Exceptions;

public class NullOpinionException extends Exception {
    public NullOpinionException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("Exception: %s", super.getMessage());
    }
}
