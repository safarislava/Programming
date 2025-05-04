package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.LoginCommand;
import ru.ifmo.se.general.command.assembler.type.ClientRequired;
import ru.ifmo.se.general.common.AbstractClient;
import ru.ifmo.se.general.common.Parser;

public class LoginCommandAssembler implements CommandAssembler, ClientRequired {
    private String username;
    private String password;
    private AbstractClient client;

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

    @Override
    public void setClient(AbstractClient client) {
        this.client = client;
    }
}
