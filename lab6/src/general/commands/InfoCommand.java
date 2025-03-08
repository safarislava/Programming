package general.commands;

import general.collection.OrganizationData;

/**
 * Realisation of CommandBuilder.
 * Provide printing info about data access object.
 *
 * @since 1.0
 * @author safarislava
 */
public class InfoCommand implements Command {
    private final OrganizationData data;

    /**
     * Standard constructor.
     *
     * @param data    Value of data access object
     * @param client Value of program
     */
    public InfoCommand(OrganizationData data) {
        this.data = data;
    }

    @Override
    public String execute() {
        return String.format("%s %s%n", data.type(), data.count());
    }
}
