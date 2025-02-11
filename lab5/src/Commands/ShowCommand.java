package Commands;

import Data.DAO;
import Entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide printing all organizations.
 *
 * @since 1.0
 * @author safarislava
 */
public class ShowCommand implements Command {
    private final DAO data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public ShowCommand(DAO data) {
        this.data = data;
    }

    @Override
    public void execute() {
        String[] keys = data.getKeys();

        for (String key : keys) {
            Organization organization = data.getOrganization(key);

            System.out.printf("Key - %s,\t %s\n", key, organization);
        }
    }
}
