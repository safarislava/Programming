package Commands;

import Data.DAO;

/**
 * Realisation of CommandBuilder.
 * Provide clearing data access object.
 *
 * @since 1.0
 * @author safarislava
 */
public class ClearCommand implements Command {
    private final DAO data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public ClearCommand(DAO data) {
        this.data = data;
    }

    @Override
    public void execute() {
        String[] keys = data.getKeys();
        for (String key : keys) {
            data.remove(key);
        }
    }
}
