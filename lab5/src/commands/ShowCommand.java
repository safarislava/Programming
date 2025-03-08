package commands;

import collection.OrganizationDAO;
import common.Client;
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
    private final Client client;

    /**
     * Standard constructor.
     *
     * @param data    Value of data access object
     * @param client Value of program
     */
    public ShowCommand(OrganizationDAO data, Client client) {
        this.data = data;
        this.client = client;
    }

    @Override
    public void execute() {
        Organization[] organizations = data.getOrganizations();

        StringBuilder text = new StringBuilder();
        for (Organization organization : organizations) {
            text.append(organization);
            text.append("\n");
        }

        client.showText(text.toString());
    }
}
