package ru.ifmo.se.general.command.builder;

import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.ExitCommand;
import ru.ifmo.se.client.Client;
import ru.ifmo.se.general.command.builder.type.CommandBuilder;

/**
 * Realisation of CommandBuilder.
 * Provide building exit command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ExitCommandBuilder implements CommandBuilder {
    private final Client client;

    /**
     * Standard constructor.
     *
     * @param client Value of lifecycle class
     */
    public ExitCommandBuilder(Client client) {
        this.client = client;
    }

    @Override
    public Command build() {
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
