package commands;

import collection.OrganizationDAO;
import common.Client;
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
    private final Client client;

    /**
     * Standard constructor.
     *
     * @param string  Value of string
     * @param data    Value of data access object
     * @param client
     */
    public FilterContainsNameCommand(String string, OrganizationDAO data, Client client) {
        this.string = string;
        this.data = data;
        this.client = client;
    }

    @Override
    public void execute() {
        StringBuilder text = new StringBuilder();

        Organization[] organizations = data.getOrganizations();
        for (Organization organization : organizations) {
            if (organization.getFullName().contains(string)) {
                text.append(organization);
                text.append("\n");
            }
        }

        client.showText(text.toString());
    }
}
