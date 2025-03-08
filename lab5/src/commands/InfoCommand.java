package commands;

import collection.OrganizationDAO;
import common.Client;

/**
 * Realisation of CommandBuilder.
 * Provide printing info about data access object.
 *
 * @since 1.0
 * @author safarislava
 */
public class InfoCommand implements Command {
    private final OrganizationDAO data;
    private final Client client;

    /**
     * Standard constructor.
     *
     * @param data    Value of data access object
     * @param client Value of program
     */
    public InfoCommand(OrganizationDAO data, Client client) {
        this.data = data;
        this.client = client;
    }

    @Override
    public void execute() {
        String text = String.format("%s %s%n", data.type(), data.count());
        client.showText(text);
    }
}
