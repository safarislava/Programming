package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.RegisterCommand;
import ru.ifmo.se.general.command.assembler.type.UserDataCommandAssembler;
import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.data.UserData;

public class RegisterCommandAssembler implements UserDataCommandAssembler {
    private String username;
    private String password;
    private UserData data;

    @Override
    public Command assemble() {
        return new RegisterCommand(username, password, data);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 2) throw new IllegalArgumentException("Wrong number of arguments");

        username = args[0];
        password = args[1];
    }

    @Override
    public String description() {
        return "Registers a new user";
    }

    @Override
    public void setUserData(UserData data) {
        this.data = data;
    }
}
