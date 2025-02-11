package commands;

import data.OrganizationDAO;
import entities.Organization;
import entities.OrganizationType;

/**
 * Realisation of CommandBuilder.
 * Provide counting organization that has lower type than entered.
 *
 * @since 1.0
 * @author safarislava
 */
public class CountLessTypeCommand implements Command {
    private final OrganizationDAO data;
    private final OrganizationType type;

    /**
     * Standard constructor.
     *
     * @param type Value of organization type
     * @param data Value of data access object
     */
    public CountLessTypeCommand(OrganizationType type, OrganizationDAO data) {
        this.type = type;
        this.data = data;
    }

    @Override
    public void execute() {
        int count = 0;

        Organization[] organizations = data.getOrganizations();
        for (Organization organization : organizations) {
            if (organization.getType().compareTo(type) > 0) count++;
        }

        System.out.println(count);
    }
}
