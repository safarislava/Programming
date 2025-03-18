package ru.ifmo.se.general.commands;

import ru.ifmo.se.general.interfaces.OrganizationData;

import java.util.List;

/**
 * Realisation of CommandBuilder.
 * Provide removing organizations that key greater than entered.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveGreaterIdCommand implements Command {
    private final OrganizationData data;
    private final int id;

    /**
     * Standard constructor.
     *
     * @param id Value of key
     * @param data Value of data access object
     */
    public RemoveGreaterIdCommand(int id, OrganizationData data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String execute() {
        List<Integer> ids = data.getIds();

        ids.stream().filter(id -> id > this.id).forEach(data::remove);

        return "Successfully removed!\n";
    }
}
