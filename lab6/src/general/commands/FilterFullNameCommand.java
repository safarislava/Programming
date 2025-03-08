package general.commands;

import general.collection.OrganizationData;
import general.entities.Organization;

import java.util.List;

/**
 * Realisation of CommandBuilder.
 * Provide printing organizations that has same full name as entered
 *
 * @since 1.0
 * @author safarislava
 */
public class FilterFullNameCommand implements Command {
    private final OrganizationData data;
    private final String fullName;

    /**
     * Standard constructor.
     *
     * @param fullName Value of full name
     * @param data     Value of data access object
     */
    public FilterFullNameCommand(String fullName, OrganizationData data) {
        this.fullName = fullName;
        this.data = data;
    }

    @Override
    public String execute() {
        StringBuilder text = new StringBuilder();

        List<Organization> organizations = data.getOrganizations();

        organizations.stream().filter(o -> o.getFullName().equals(fullName))
                .forEach(o -> text.append(String.format("%s%n", o)));

        if (text.isEmpty()) {
            return "No organizations found!\n";
        }

        return text.toString();
    }
}

