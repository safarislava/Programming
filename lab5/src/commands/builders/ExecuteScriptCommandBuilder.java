package commands.builders;

import commands.Command;
import commands.ExecuteScriptCommand;
import data.OrganizationDAO;

/**
 * Realisation of CommandBuilder.
 * Provide building execute script command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ExecuteScriptCommandBuilder implements CommandBuilder {
    private final OrganizationDAO data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public ExecuteScriptCommandBuilder(OrganizationDAO data) {
        this.data = data;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        return new ExecuteScriptCommand(args[0], data);
    }

    @Override
    public String description() {
        return "Executes a script";
    }
}
