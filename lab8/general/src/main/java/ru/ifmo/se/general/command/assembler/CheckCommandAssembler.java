package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.CheckConnectionCommand;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.assembler.type.ServerRequired;
import ru.ifmo.se.general.parser.Parser;

public class CheckCommandAssembler implements ServerRequired {
    @Override
    public Command assemble() {
        return new CheckConnectionCommand();
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
    }

    @Override
    public String description() {
        return "Check connection to server";
    }
}
