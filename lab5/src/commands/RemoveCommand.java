package commands;

import collection.OrganizationDAO;

/**
 * Realisation of CommandBuilder.
 * Provide removing organization from data access object.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveCommand implements Command {
    private final OrganizationDAO data;
    private final int id;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     * @param id Value of key
     */
    public RemoveCommand(OrganizationDAO data, int id) {
        this.data = data;
        this.id = id;
    }

    @Override
    public void execute() {
        data.remove(id);
    }
}
