package general.commands;

import general.collection.OrganizationData;
import general.entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide insertion of organization into data access object
 *
 * @since 1.0
 * @author safarislava
 */
public class InsertCommand implements Command {
    private final OrganizationData data;
    private final Organization organization;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     * @param organization Value of organization
     */
    public InsertCommand(OrganizationData data, Organization organization) {
        this.data = data;
        this.organization = organization;
    }

    @Override
    public String execute() {
        data.insert(organization);
        return "Successfully inserted!\n";
    }
}
