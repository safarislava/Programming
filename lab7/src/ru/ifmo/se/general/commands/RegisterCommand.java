package ru.ifmo.se.general.commands;

import ru.ifmo.se.general.interfaces.UserData;

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
        try {
            data.register(username, password);
        }
        catch (Exception e) {
            return "This username already exists.\n";
        }

        return "Successfully registered new user!\n";
    }
}
