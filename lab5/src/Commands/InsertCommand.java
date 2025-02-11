package Commands;

import Data.DAO;
import Entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide insertion of organization into data access object
 *
 * @since 1.0
 * @author safarislava
 */
public class InsertCommand implements Command {
    private final DAO data;
    private final String key;
    private final Organization organization;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     * @param key Value of key
     * @param organization Value of organization
     */
    public InsertCommand(DAO data, String key, Organization organization) {
        this.data = data;
        this.key = key;
        this.organization = organization;
    }

    @Override
    public void execute() {
        data.insert(key, organization);
    }
}
