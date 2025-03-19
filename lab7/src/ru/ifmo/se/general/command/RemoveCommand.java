package ru.ifmo.se.general.command;

import ru.ifmo.se.general.data.OrganizationData;

/**
 * Realisation of CommandBuilder.
 * Provide removing organization from data access object.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveCommand implements Command {
    private final OrganizationData data;
    private final int id;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     * @param id Value of key
     */
    public RemoveCommand(OrganizationData data, int id) {
        this.data = data;
        this.id = id;
    }

    @Override
    public String execute() {
        return data.remove(id);
    }
}
