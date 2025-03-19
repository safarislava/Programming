package ru.ifmo.se.general.contract;

import ru.ifmo.se.general.command.assembler.type.CommandAssembler;

import java.io.Serializable;

public class Request implements Serializable {
    public String username;
    public String password;
    public CommandAssembler commandAssembler;

    public Request(String username, String password, CommandAssembler commandAssembler) {
        this.username = username;
        this.password = password;
        this.commandAssembler = commandAssembler;
    }
}
