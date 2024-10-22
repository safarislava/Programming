package Exceptions;

public class MalishEnemyException extends Exception {
    public MalishEnemyException() {}

    public MalishEnemyException(String message) {
        super(message);
    }

    public MalishEnemyException (Throwable cause) {
        super (cause);
    }

    public MalishEnemyException (String message, Throwable cause) {
        super (message, cause);
    }
}
