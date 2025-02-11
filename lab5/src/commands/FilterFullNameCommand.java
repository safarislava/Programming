package commands;

import data.OrganizationDAO;
import entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide printing organizations that has same full name as entered
 *
 * @since 1.0
 * @author safarislava
 */
public class FilterFullNameCommand implements Command {
    private final OrganizationDAO data;
    private final String fullName;

    /**
     * Standard constructor.
     *
     * @param fullName Value of full name
     * @param data Value of data access object
     */
    public FilterFullNameCommand(String fullName, OrganizationDAO data) {
        this.fullName = fullName;
        this.data = data;
    }

    @Override
    public void execute() {
        Organization[] organizations = data.getOrganizations();
        for (Organization organization : organizations) {
            if (organization.getFullName().equals(fullName)) {
                System.out.println(organization);
            }
        }
    }
}

