package ru.ifmo.se.general.command;

import ru.ifmo.se.client.Client;

/**
 * Realisation of CommandAssembler.
 * Provide stopping lifecycle of program.
 *
 * @since 1.0
 * @author safarislava
 */
public class ExitCommand implements Command {
    private final Client client;

    /**
     * Standard constructor.
     *
     * @param client Value of lifecycle class
     */
    public ExitCommand(Client client) {
        this.client = client;
    }

    @Override
    public String execute() {
        client.stop();

        return "Successfully exited!\n";
    }
}
