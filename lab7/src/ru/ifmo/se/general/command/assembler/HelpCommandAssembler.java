package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.client.command.CommandManager;
import ru.ifmo.se.general.command.HelpCommand;
import ru.ifmo.se.general.command.assembler.type.CommandAssembler;

/**
 * Realisation of CommandAssembler.
 * Provide assembling help command.
 *
 * @since 1.0
 * @author safarislava
 */
public class HelpCommandAssembler implements CommandAssembler {
    private final CommandManager controller;

    /**
     * Standard constructor.
     *
     * @param controller Value of recognizing command controller
     */
    public HelpCommandAssembler(CommandManager controller) {
        this.controller = controller;
    }

    @Override
    public Command assemble() {
        return new HelpCommand(controller);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
    }

    @Override
    public String description() {
        return "Print all commands";
    }
}
