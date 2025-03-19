package ru.ifmo.se.general.command;

import ru.ifmo.se.general.data.OrganizationData;

import java.util.List;

/**
 * Realisation of CommandBuilder.
 * Provide clearing data access object.
 *
 * @since 1.0
 * @author safarislava
 */
public class ClearCommand implements Command {
    private final OrganizationData data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public ClearCommand(OrganizationData data) {
        this.data = data;
    }

    @Override
    public String execute() {
        List<Integer> ids = data.getIds();
        for (Integer id : ids) {
            data.remove(id);
        }

        return "Successfully cleared!\n";
    }
}
