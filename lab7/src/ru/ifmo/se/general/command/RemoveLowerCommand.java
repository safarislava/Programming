package ru.ifmo.se.general.command;

import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.entity.Organization;

import java.util.List;

/**
 * Realisation of CommandBuilder.
 * Provide removing organizations that lower than entered.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveLowerCommand implements Command {
    private final OrganizationData data;
    private final Organization organization;

    /**
     * Standard constructor.
     *
     * @param organization Value of organization
     * @param data Value of data access object
     */
    public RemoveLowerCommand(Organization organization, OrganizationData data) {
        this.organization = organization;
        this.data = data;
    }

    @Override
    public String execute() {
        StringBuilder result = new StringBuilder();

        List<Organization> organizations = data.getOrganizations();

        organizations.stream().filter(o -> o.compareTo(organization) < 0)
                .forEach(o -> result.append(data.remove(o.getId())));

        return result.toString();
    }
}
