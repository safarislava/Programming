package ru.ifmo.se.general.commands;

import ru.ifmo.se.general.interfaces.OrganizationData;
import ru.ifmo.se.general.entities.Organization;

import java.util.List;

/**
 * Realisation of CommandBuilder.
 * Provide printing all organizations.
 *
 * @since 1.0
 * @author safarislava
 */
public class ShowCommand implements Command {
    private final OrganizationData data;

    /**
     * Standard constructor.
     *
     * @param data    Value of data access object
     */
    public ShowCommand(OrganizationData data) {
        this.data = data;
    }

    @Override
    public String execute() {
        StringBuilder text = new StringBuilder();
        List<Organization> organizations = data.getOrganizations();

        organizations.forEach(o -> text.append(String.format("%s%n", o)));

        if (text.isEmpty()) {
            return "No organizations found!\n";
        }

        return text.toString();
    }
}
