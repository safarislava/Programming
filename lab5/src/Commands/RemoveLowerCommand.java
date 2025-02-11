package Commands;

import Data.DAO;
import Entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide removing organizations that lower than entered.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveLowerCommand implements Command {
    private final DAO data;
    private final Organization organization;

    /**
     * Standard constructor.
     *
     * @param organization Value of organization
     * @param data Value of data access object
     */
    public RemoveLowerCommand(Organization organization, DAO data) {
        this.organization = organization;
        this.data = data;
    }

    @Override
    public void execute() {
        String[] keys = data.getKeys();
        for (String key : keys) {
            if (data.getOrganization(key).compareTo(organization) < 0) {
                data.remove(key);
            }
        }
    }
}
