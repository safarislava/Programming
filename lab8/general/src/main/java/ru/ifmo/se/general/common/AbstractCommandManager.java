package ru.ifmo.se.general.common;

import ru.ifmo.se.general.command.assembler.CommandAssembler;

import java.util.Map;

public interface AbstractCommandManager {
    Map<String, CommandAssembler> getCommandAssemblers();
}
