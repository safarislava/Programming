package ru.ifmo.se.general.command;

import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.entity.Organization;

import java.util.List;

/**
 * Realisation of CommandAssembler.
 * Provide printing organizations that contains in name entered string.
 *
 * @since 1.0
 * @author safarislava
 */
public class FilterContainsNameCommand implements Command {
    private final OrganizationData data;
    private final String string;

    /**
     * Standard constructor.
     *
     * @param string  Value of string
     * @param data    Value of data access object
     */
    public FilterContainsNameCommand(String string, OrganizationData data) {
        this.string = string;
        this.data = data;
    }

    @Override
    public String execute() {
        StringBuilder text = new StringBuilder();

        List<Organization> organizations = data.getOrganizations();

        organizations.stream().filter(o -> o.getFullName().contains(string))
                .forEach(o -> text.append(String.format("%s%n", o)));

        if (text.toString().equals("\n")) {
            return "No organizations found!\n";
        }

        return text.toString();
    }
}
