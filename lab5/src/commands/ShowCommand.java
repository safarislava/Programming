package commands;

import data.OrganizationDAO;
import entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide printing all organizations.
 *
 * @since 1.0
 * @author safarislava
 */
public class ShowCommand implements Command {
    private final OrganizationDAO data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public ShowCommand(OrganizationDAO data) {
        this.data = data;
    }

    @Override
    public void execute() {
        Organization[] organizations = data.getOrganizations();

        for (Organization organization : organizations) {
            System.out.println(organization);
        }
    }
}
