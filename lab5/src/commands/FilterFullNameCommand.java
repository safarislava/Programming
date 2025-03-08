package commands;

import collection.OrganizationDAO;
import common.Client;
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
    private final Client client;

    /**
     * Standard constructor.
     *
     * @param fullName Value of full name
     * @param data     Value of data access object
     * @param client
     */
    public FilterFullNameCommand(String fullName, OrganizationDAO data, Client client) {
        this.fullName = fullName;
        this.data = data;
        this.client = client;
    }

    @Override
    public void execute() {
        StringBuilder text = new StringBuilder();

        Organization[] organizations = data.getOrganizations();
        for (Organization organization : organizations) {
            if (organization.getFullName().equals(fullName)) {
                text.append(organization);
                text.append("\n");
            }
        }

        client.showText(text.toString());
    }
}

