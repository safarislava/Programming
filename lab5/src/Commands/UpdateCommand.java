package Commands;

import Data.DAO;
import Entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide updating(editing) current organization.
 *
 * @since 1.0
 * @author safarislava
 */
public class UpdateCommand implements Command {
    private final DAO data;
    private final int id;
    private final Organization organization;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     * @param id Value of id
     * @param organization Value of organization
     */
    public UpdateCommand(DAO data, int id, Organization organization) {
        this.data = data;
        this.id = id;
        this.organization = organization;
    }

    @Override
    public void execute() {
        data.update(id, organization);
    }
}
