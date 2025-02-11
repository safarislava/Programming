package commands.builders;

import commands.Command;
import commands.RemoveGreaterIdCommand;
import data.OrganizationDAO;

/**
 * Realisation of CommandBuilder.
 * Provide building remove greater key command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveGreaterIdCommandBuilder implements CommandBuilder {
    private final OrganizationDAO data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public RemoveGreaterIdCommandBuilder(OrganizationDAO data) {
        this.data = data;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        return new RemoveGreaterIdCommand(Integer.parseInt(args[0]), data);
    }

    @Override
    public String description() {
        return "Remove all organizations, which have a greater id";
    }
}
