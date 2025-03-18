package ru.ifmo.se.general.commands;

import ru.ifmo.se.client.Client;

public class LoginCommand implements Command {
    private final String username;
    private final String password;
    private final Client client;

    public LoginCommand(String username, String password, Client client) {
        this.username = username;
        this.password = password;
        this.client = client;
    }

    @Override
    public String execute() {
        client.setUsername(username);
        client.setPassword(password);

//        boolean logging = data.checkUserPassword(username, password);
//
//        if (!logging) return "Something went wrong!\n";
//
        return "Set username and password\n";
    }
}
