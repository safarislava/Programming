package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.ExitCommand;
import ru.ifmo.se.client.Client;
import ru.ifmo.se.general.command.assembler.type.CommandAssembler;

/**
 * Realisation of CommandAssembler.
 * Provide assembling exit command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ExitCommandAssembler implements CommandAssembler {
    private final Client client;

    /**
     * Standard constructor.
     *
     * @param client Value of lifecycle class
     */
    public ExitCommandAssembler(Client client) {
        this.client = client;
    }

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
}
