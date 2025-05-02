package ru.ifmo.se.general.command;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.contract.CodePhrase;

public class CheckConnectionCommand implements Command {
    @Override
    public String execute() {
        return CodePhrase.SUCCESSFUL;
    }
}
