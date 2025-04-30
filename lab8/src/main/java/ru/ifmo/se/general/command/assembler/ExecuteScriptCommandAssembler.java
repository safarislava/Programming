package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.client.Client;
import ru.ifmo.se.general.parser.Parser;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.ExecuteScriptCommand;
import ru.ifmo.se.general.command.assembler.type.ClientRequired;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Realisation of CommandAssembler.
 * Provide assembling execute script command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ExecuteScriptCommandAssembler implements CommandAssembler, ClientRequired {
    private Client client;
    private Path file;

    @Override
    public Command assemble() {
        return new ExecuteScriptCommand(file, client);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        file = Paths.get(args[0]);
        if (!Files.exists(file)) throw new NullPointerException("File does not exist");
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
