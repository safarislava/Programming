package general.commands.builders;

import client.Client;
import client.common.Input;
import general.commands.Command;
import general.commands.ExecuteScriptCommand;

import java.util.Set;

/**
 * Realisation of CommandBuilder.
 * Provide building execute script command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ExecuteScriptCommandBuilder implements CommandBuilder {
    private final Client client;
    private String fileName;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public ExecuteScriptCommandBuilder(Client client) {
        this.client = client;
    }

    @Override
    public Command build() {
        return new ExecuteScriptCommand(fileName, client);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        fileName = args[0];
    }

    @Override
    public String description() {
        return "Executes a script";
    }
}
