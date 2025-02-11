package Commands;

import Data.DAO;

/**
 * Realisation of CommandBuilder.
 * Provide removing organizations that key greater than entered.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveGreaterKeyCommand implements Command {
    private final DAO data;
    private final String key;

    /**
     * Standard constructor.
     *
     * @param key Value of key
     * @param data Value of data access object
     */
    public RemoveGreaterKeyCommand(String key, DAO data) {
        this.key = key;
        this.data = data;
    }

    @Override
    public void execute() {
        String[] keys = data.getKeys();
        for (String key : keys) {
            if (key.compareTo(this.key) > 0) {
                data.remove(key);
            }
        }
    }
}
