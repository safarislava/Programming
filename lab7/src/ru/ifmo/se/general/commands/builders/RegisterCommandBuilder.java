package ru.ifmo.se.general.commands.builders;

import ru.ifmo.se.general.commands.Command;
import ru.ifmo.se.general.commands.RegisterCommand;
import ru.ifmo.se.general.commands.builders.interfaces.UserDataCommandBuilder;
import ru.ifmo.se.general.interfaces.Input;
import ru.ifmo.se.general.interfaces.UserData;

public class RegisterCommandBuilder implements UserDataCommandBuilder {
    private String username;
    private String password;
    private UserData data;

    @Override
    public Command build() {
        return new RegisterCommand(username, password, data);
    }

    @Override
    public void setArguments(String[] args, Input input) {
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
