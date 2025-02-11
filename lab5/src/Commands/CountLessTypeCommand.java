package Commands;

import Data.DAO;
import Entities.OrganizationType;

/**
 * Realisation of CommandBuilder.
 * Provide counting organization that has lower type than entered.
 *
 * @since 1.0
 * @author safarislava
 */
public class CountLessTypeCommand implements Command {
    private final DAO data;
    private final OrganizationType type;

    /**
     * Standard constructor.
     *
     * @param type Value of organization type
     * @param data Value of data access object
     */
    public CountLessTypeCommand(OrganizationType type, DAO data) {
        this.type = type;
        this.data = data;
    }

    @Override
    public void execute() {
        int count = 0;

        String[] keys = data.getKeys();
        for (String key : keys) {
            if (data.getOrganization(key).getType().compareTo(type) > 0) count++;
        }

        System.out.println(count);
    }
}
