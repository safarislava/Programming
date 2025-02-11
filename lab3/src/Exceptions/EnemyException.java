package Exceptions;

public class EnemyException extends RuntimeException {
    public EnemyException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("Драка невозможна, так как %s", super.getMessage());
    }
}
