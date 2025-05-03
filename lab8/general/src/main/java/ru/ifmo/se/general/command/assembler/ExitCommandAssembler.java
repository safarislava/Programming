package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.ExitCommand;
import ru.ifmo.se.general.command.assembler.type.ClientRequired;
import ru.ifmo.se.general.common.AbstractClient;
import ru.ifmo.se.general.parser.Parser;

/**
 * Realisation of CommandAssembler.
 * Provide assembling exit command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ExitCommandAssembler implements CommandAssembler, ClientRequired {
    private AbstractClient client;

    @Override
    public Command assemble() {
        return new ExitCommand(client);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
    }

    @Override
    public String description(){
        return "Exit the program";
    }

    @Override
    public void setClient(AbstractClient client) {
        this.client = client;
    }
}
