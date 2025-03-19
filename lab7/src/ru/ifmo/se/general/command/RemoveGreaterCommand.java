package ru.ifmo.se.general.command;

import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.entity.Organization;

import java.util.List;

/**
 * Realisation of CommandBuilder.
 * Provide removing organizations that greater than entered.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveGreaterCommand implements Command {
    private final OrganizationData data;
    private final Organization organization;

    /**
     * Standard constructor.
     *
     * @param organization Value of organization
     * @param data Value of data access object
     */
    public RemoveGreaterCommand(Organization organization, OrganizationData data) {
        this.organization = organization;
        this.data = data;
    }

    @Override
    public String execute() {
        List<Organization> organizations = data.getOrganizations();

        organizations.stream().filter(o -> o.compareTo(organization) > 0)
                .forEach(o -> data.remove(o.getId()));

        return "Successfully removed!\n";
    }
}
