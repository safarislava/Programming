package commands;

import data.OrganizationDAO;
import entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide insertion of organization into data access object
 *
 * @since 1.0
 * @author safarislava
 */
public class InsertCommand implements Command {
    private final OrganizationDAO data;
    private final Organization organization;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     * @param organization Value of organization
     */
    public InsertCommand(OrganizationDAO data, Organization organization) {
        this.data = data;
        this.organization = organization;
    }

    @Override
    public void execute() {
        data.insert(organization);
    }
}
