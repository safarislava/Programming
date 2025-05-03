package ru.ifmo.se.general.command.assembler.type;

import ru.ifmo.se.general.common.AbstractCommandManager;

public interface CommandManagerRequired {
    void setCommandManager(AbstractCommandManager commandManager);
}
