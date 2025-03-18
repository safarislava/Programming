package ru.ifmo.se.general.commands;

import ru.ifmo.se.general.interfaces.OrganizationData;
import ru.ifmo.se.general.entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide updating(editing) current organization.
 *
 * @since 1.0
 * @author safarislava
 */
public class UpdateCommand implements Command {
    private final OrganizationData data;
    private final int id;
    private final Organization organization;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     * @param id Value of id
     * @param organization Value of organization
     */
    public UpdateCommand(OrganizationData data, int id, Organization organization) {
        this.data = data;
        this.id = id;
        this.organization = organization;
    }

    @Override
    public String execute() {
        return data.update(id, organization);
    }
}
