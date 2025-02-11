package commands;

import data.OrganizationDAO;
import entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide removing organizations that lower than entered.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveLowerCommand implements Command {
    private final OrganizationDAO data;
    private final Organization organization;

    /**
     * Standard constructor.
     *
     * @param organization Value of organization
     * @param data Value of data access object
     */
    public RemoveLowerCommand(Organization organization, OrganizationDAO data) {
        this.organization = organization;
        this.data = data;
    }

    @Override
    public void execute() {
        Organization[] organizations = data.getOrganizations();
        for (Organization organization : organizations) {
            if (organization.compareTo(this.organization) < 0) {
                data.remove(organization.getId());
            }
        }
    }
}
