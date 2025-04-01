package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.client.Client;
import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.ExecuteScriptCommand;
import ru.ifmo.se.general.command.assembler.type.ClientRequired;

/**
 * Realisation of CommandAssembler.
 * Provide assembling execute script command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ExecuteScriptCommandAssembler implements CommandAssembler, ClientRequired {
    private Client client;
    private String fileName;

    @Override
    public Command assemble() {
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

    @Override
    public void setClient(Client client) {
        this.client = client;
    }
}
