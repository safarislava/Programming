package ru.ifmo.se.general.common;

import ru.ifmo.se.general.command.assembler.CommandAssembler;

import java.util.Map;

public interface CommandManagerInterface {
    Map<String, CommandAssembler> getCommandAssemblers();
}
