package commands;

import data.OrganizationDAO;
import entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide printing organizations that contains in name entered string.
 *
 * @since 1.0
 * @author safarislava
 */
public class FilterContainsNameCommand implements Command {
    private final OrganizationDAO data;
    private final String string;

    /**
     * Standard constructor.
     *
     * @param string Value of string
     * @param data Value of data access object
     */
    public FilterContainsNameCommand(String string, OrganizationDAO data) {
        this.string = string;
        this.data = data;
    }

    @Override
    public void execute() {
        Organization[] organizations = data.getOrganizations();
        for (Organization organization : organizations) {
            if (organization.getFullName().contains(string)) {
                System.out.println(organization);
            }
        }
    }
}
