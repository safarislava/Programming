package ru.ifmo.se.general.command;

import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.entity.Organization;

/**
 * Realisation of CommandAssembler.
 * Provide insertion of organization into data access object
 *
 * @since 1.0
 * @author safarislava
 */
public class InsertCommand implements Command {
    private final OrganizationData data;
    private final Organization organization;
    private final String creator;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     * @param organization Value of organization
     */
    public InsertCommand(OrganizationData data, Organization organization, String creator) {
        this.data = data;
        this.organization = organization;
        this.creator = creator;
    }

    @Override
    public String execute() {
        return data.insert(organization, creator);
    }
}
