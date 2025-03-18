package ru.ifmo.se.general.commands.builders;

import ru.ifmo.se.client.Client;
import ru.ifmo.se.general.interfaces.Input;
import ru.ifmo.se.general.commands.Command;
import ru.ifmo.se.general.commands.ExecuteScriptCommand;
import ru.ifmo.se.general.commands.builders.interfaces.CommandBuilder;

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
     * @param client Value of client
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
        return "Execute a script";
    }
}
