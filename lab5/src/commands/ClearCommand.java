package commands;

import data.OrganizationDAO;

/**
 * Realisation of CommandBuilder.
 * Provide clearing data access object.
 *
 * @since 1.0
 * @author safarislava
 */
public class ClearCommand implements Command {
    private final OrganizationDAO data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public ClearCommand(OrganizationDAO data) {
        this.data = data;
    }

    @Override
    public void execute() {
        Integer[] ids = data.getIds();
        for (Integer id : ids) {
            data.remove(id);
        }
    }
}
