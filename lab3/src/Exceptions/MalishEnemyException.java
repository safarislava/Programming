package Exceptions;

public class MalishEnemyException extends Exception {
    public MalishEnemyException() {}

    public MalishEnemyException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Exception: Малыш, не может драться, так как " + super.getMessage();
    }
}
