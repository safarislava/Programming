package commands.builders;

import commands.Command;
import commands.RemoveCommand;
import collection.OrganizationDAO;

/**
 * Realisation of CommandBuilder.
 * Provide building remove command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveCommandBuilder implements CommandBuilder {
    private final OrganizationDAO data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public RemoveCommandBuilder(OrganizationDAO data) {
        this.data = data;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");
        return new RemoveCommand(data, Integer.parseInt(args[0]));
    }

    @Override
    public String description() {
        return "Remove element by id";
    }
}
