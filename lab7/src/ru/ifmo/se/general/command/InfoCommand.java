package ru.ifmo.se.general.command;

import ru.ifmo.se.general.data.OrganizationData;

/**
 * Realisation of CommandAssembler.
 * Provide printing info about data access object.
 *
 * @since 1.0
 * @author safarislava
 */
public class InfoCommand implements Command {
    private final OrganizationData data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public InfoCommand(OrganizationData data) {
        this.data = data;
    }

    @Override
    public String execute() {
        return String.format("%s %s%n", data.type(), data.count());
    }
}
