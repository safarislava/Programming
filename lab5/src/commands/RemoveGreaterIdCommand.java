package commands;

import data.OrganizationDAO;

/**
 * Realisation of CommandBuilder.
 * Provide removing organizations that key greater than entered.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveGreaterIdCommand implements Command {
    private final OrganizationDAO data;
    private final int id;

    /**
     * Standard constructor.
     *
     * @param id Value of key
     * @param data Value of data access object
     */
    public RemoveGreaterIdCommand(int id, OrganizationDAO data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public void execute() {
        Integer[] ids = data.getIds();
        for (int id : ids) {
            if (id > this.id) {
                data.remove(id);
            }
        }
    }
}
