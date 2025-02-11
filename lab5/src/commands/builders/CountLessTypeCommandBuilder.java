package commands.builders;

import commands.Command;
import commands.CountLessTypeCommand;
import data.OrganizationDAO;
import entities.OrganizationType;

/**
 * Realisation of CommandBuilder.
 * Provide building count less type command.
 *
 * @since 1.0
 * @author safarislava
 */
public class CountLessTypeCommandBuilder implements CommandBuilder {
    private final OrganizationDAO data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public CountLessTypeCommandBuilder(OrganizationDAO data) {
        this.data = data;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        OrganizationType type = null;
        for (OrganizationType organizationType : OrganizationType.values()) {
            if (organizationType.name().equals(args[0])) type = organizationType;
        }
        if (type == null) throw new IllegalArgumentException("Invalid organization type");

        return new CountLessTypeCommand(type, data);
    }

    @Override
    public String description() {
        return "Print count of organizations which has less types";
    }
}
