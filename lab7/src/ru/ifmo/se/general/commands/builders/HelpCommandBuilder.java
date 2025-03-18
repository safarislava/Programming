package ru.ifmo.se.general.commands.builders;

import ru.ifmo.se.general.interfaces.Input;
import ru.ifmo.se.general.commands.Command;
import ru.ifmo.se.client.commands.CommandManager;
import ru.ifmo.se.general.commands.HelpCommand;
import ru.ifmo.se.general.commands.builders.interfaces.CommandBuilder;

/**
 * Realisation of CommandBuilder.
 * Provide building help command.
 *
 * @since 1.0
 * @author safarislava
 */
public class HelpCommandBuilder implements CommandBuilder {
    private final CommandManager controller;

    /**
     * Standard constructor.
     *
     * @param controller Value of recognizing command controller
     */
    public HelpCommandBuilder(CommandManager controller) {
        this.controller = controller;
    }

    @Override
    public Command build() {
        return new HelpCommand(controller);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
    }

    @Override
    public String description() {
        return "Print all commands";
    }
}
