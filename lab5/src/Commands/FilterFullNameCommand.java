package Commands;

import Data.DAO;
import Entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide printing organizations that has same full name as entered
 *
 * @since 1.0
 * @author safarislava
 */
public class FilterFullNameCommand implements Command {
    private final DAO data;
    private final String fullName;

    /**
     * Standard constructor.
     *
     * @param fullName Value of full name
     * @param data Value of data access object
     */
    public FilterFullNameCommand(String fullName, DAO data) {
        this.fullName = fullName;
        this.data = data;
    }

    @Override
    public void execute() {
        String[] keys = data.getKeys();
        for (String key : keys) {
            Organization organization = data.getOrganization(key);
            if (organization.getFullName().equals(fullName)) {
                System.out.printf("Key - %s,\t %s\n", key, organization);
            }
        }
    }
}

