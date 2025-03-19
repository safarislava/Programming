package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.client.Client;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.LoginCommand;
import ru.ifmo.se.general.command.assembler.type.CommandAssembler;
import ru.ifmo.se.general.Parser;

public class LoginCommandAssembler implements CommandAssembler {
    private String username;
    private String password;
    private final Client client;

    public LoginCommandAssembler(Client client) {
        this.client = client;
    }

    @Override
    public Command assemble() {
        return new LoginCommand(username, password, client);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 2) throw new IllegalArgumentException("Invalid number of arguments");

        username = args[0];
        password = args[1];
    }

    @Override
    public String description() {
        return "Login into existing user";
    }
}
