package ru.ifmo.se.general.contracts;

import ru.ifmo.se.general.commands.builders.interfaces.CommandBuilder;

import java.io.Serializable;

public class Request implements Serializable {
    public String username;
    public String password;
    public CommandBuilder commandBuilder;

    public Request(String username, String password, CommandBuilder commandBuilder) {
        this.username = username;
        this.password = password;
        this.commandBuilder = commandBuilder;
    }
}
