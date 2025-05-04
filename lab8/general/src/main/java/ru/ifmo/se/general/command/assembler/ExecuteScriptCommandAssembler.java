package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.ExecuteScriptCommand;
import ru.ifmo.se.general.command.assembler.type.ClientRequired;
import ru.ifmo.se.general.common.AbstractClient;
import ru.ifmo.se.general.common.Parser;


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
    private AbstractClient client;
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
    public void setClient(AbstractClient client) {
        this.client = client;
    }
}
