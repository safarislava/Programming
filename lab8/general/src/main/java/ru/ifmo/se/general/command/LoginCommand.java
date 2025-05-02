package ru.ifmo.se.general.command;

import ru.ifmo.se.general.common.ClientInterface;

public class LoginCommand implements Command {
    private final String username;
    private final String password;
    private final ClientInterface client;

    public LoginCommand(String username, String password, ClientInterface client) {
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
