package ru.ifmo.se.general.command;

import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.entity.Organization;
import ru.ifmo.se.general.entity.OrganizationType;

import java.util.List;

/**
 * Realisation of CommandAssembler.
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
