package Commands;

/**
 * Interface of command. Command must be executing without arguments (see CommandBuilder).
 *
 * @since 1.0
 * @author safarislava
 */
public interface Command {
    /**
     * Main method provides work of command
     */
    void execute();
}
