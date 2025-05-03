package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.HelpCommand;
import ru.ifmo.se.general.command.assembler.type.CommandManagerRequired;
import ru.ifmo.se.general.common.AbstractCommandManager;
import ru.ifmo.se.general.parser.Parser;

/**
 * Realisation of CommandAssembler.
 * Provide assembling help command.
 *
 * @since 1.0
 * @author safarislava
 */
public class HelpCommandAssembler implements CommandAssembler, CommandManagerRequired {
    private AbstractCommandManager commandManager;

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
    public void setCommandManager(AbstractCommandManager commandManager) {
        this.commandManager = commandManager;
    }
}
