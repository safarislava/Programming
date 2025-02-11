package Commands;

import Data.DAO;
import Entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide printing organizations that contains in name entered string.
 *
 * @since 1.0
 * @author safarislava
 */
public class FilterContainsNameCommand implements Command {
    private final DAO data;
    private final String string;

    /**
     * Standard constructor.
     *
     * @param string Value of string
     * @param data Value of data access object
     */
    public FilterContainsNameCommand(String string, DAO data) {
        this.string = string;
        this.data = data;
    }

    @Override
    public void execute() {
        String[] keys = data.getKeys();
        for (String key : keys) {
            Organization organization = data.getOrganization(key);
            if (organization.getFullName().contains(string)) {
                System.out.printf("Key - %s,\t %s\n", key, organization);
            }
        }
    }
}
