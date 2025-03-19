package ru.ifmo.se.general.command.builder;

import ru.ifmo.se.client.Client;
import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.ExecuteScriptCommand;
import ru.ifmo.se.general.command.builder.type.CommandBuilder;

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
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        fileName = args[0];
    }

    @Override
    public String description() {
        return "Execute a script";
    }
}
