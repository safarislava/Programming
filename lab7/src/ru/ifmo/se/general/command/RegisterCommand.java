package ru.ifmo.se.general.command;

import ru.ifmo.se.general.data.UserData;

public class RegisterCommand implements Command {
    private final String username;
    private final String password;
    private final UserData data;

    public RegisterCommand(String username, String password, UserData data) {
        this.username = username;
        this.password = password;
        this.data = data;
    }

    @Override
    public String execute() {
        return data.register(username, password);
    }
}
