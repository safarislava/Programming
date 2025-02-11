package commands.builders;

import commands.ClearCommand;
import commands.Command;
import data.OrganizationDAO;

/**
 * Realisation of CommandBuilder.
 * Provide building clear command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ClearCommandBuilder implements CommandBuilder {
    private final ClearCommand clearCommand;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public ClearCommandBuilder(OrganizationDAO data) {
        clearCommand = new ClearCommand(data);
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
        return clearCommand;
    }

    @Override
    public String description() {
        return "Clear collection";
    }
}
