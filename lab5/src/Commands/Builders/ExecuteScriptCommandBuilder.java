package Commands.Builders;

import Commands.Command;
import Commands.ExecuteScriptCommand;
import Data.DAO;

/**
 * Realisation of CommandBuilder.
 * Provide building execute script command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ExecuteScriptCommandBuilder implements CommandBuilder {
    private final DAO data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public ExecuteScriptCommandBuilder(DAO data) {
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
