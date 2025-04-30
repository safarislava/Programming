package ru.ifmo.se.general.contract;

import ru.ifmo.se.general.command.assembler.CommandAssembler;

import java.io.Serializable;

/**
 * Class for transfer data. Need for request from client to ru.ifmo.se.server.
 *
 * @since 3.0
 * @author safarislava
 */
public class Request implements Serializable {
    public String username;
    public String password;
    public CommandAssembler commandAssembler;

    /**
     * Standard constructor
     */
    public Request() {}

    /**
     * Standard constructor.
     *
     * @param username Value of username
     * @param password Value of password
     * @param commandAssembler Value of commandAssembler
     */
    public Request(String username, String password, CommandAssembler commandAssembler) {
        this.username = username;
        this.password = password;
        this.commandAssembler = commandAssembler;
    }
}
