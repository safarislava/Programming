package ru.ifmo.se.general.command.assembler.type;

import ru.ifmo.se.general.common.CommandManagerInterface;

public interface CommandManagerRequired {
    void setCommandManager(CommandManagerInterface commandManager);
}
