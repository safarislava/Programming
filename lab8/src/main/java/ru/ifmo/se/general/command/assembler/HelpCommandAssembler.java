package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.parser.Parser;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.client.command.CommandManager;
import ru.ifmo.se.general.command.HelpCommand;
import ru.ifmo.se.general.command.assembler.type.CommandManagerRequired;

/**
 * Realisation of CommandAssembler.
 * Provide assembling help command.
 *
 * @since 1.0
 * @author safarislava
 */
public class HelpCommandAssembler implements CommandAssembler, CommandManagerRequired {
    private CommandManager commandManager;

    @Override
    public Command assemble() {
        return new HelpCommand(commandManager);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
    }

    @Override
    public String description() {
        return "Print all commands";
    }

    @Override
    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}
