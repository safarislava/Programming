package general.commands;

import general.collection.OrganizationData;
import general.entities.Organization;
import general.entities.OrganizationType;

import java.util.List;

/**
 * Realisation of CommandBuilder.
 * Provide counting organization that has lower type than entered.
 *
 * @since 1.0
 * @author safarislava
 */
public class CountLessTypeCommand implements Command {
    private final OrganizationData data;
    private final OrganizationType type;

    /**
     * Standard constructor.
     *
     * @param type    Value of organization type
     * @param data    Value of data access object
     */
    public CountLessTypeCommand(OrganizationType type, OrganizationData data) {
        this.type = type;
        this.data = data;
    }

    @Override
    public String execute() {
        List<Organization> organizations = data.getOrganizations();

        long count = organizations.stream().filter(o -> (o.getType().compareTo(type) > 0)).count();

        return String.format("%d%n", count);
    }
}
