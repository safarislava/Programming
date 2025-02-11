package Commands;

import Data.DAO;

/**
 * Realisation of CommandBuilder.
 * Provide removing organization from data access object.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveCommand implements Command {
    private final DAO data;
    private final String key;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     * @param key Value of key
     */
    public RemoveCommand(DAO data, String key) {
        this.data = data;
        this.key = key;
    }

    @Override
    public void execute() {
        data.remove(key);
    }
}
