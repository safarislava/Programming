package ru.ifmo.se.general.commands;

import ru.ifmo.se.client.Client;
import ru.ifmo.se.general.interfaces.Input;
import ru.ifmo.se.client.input.ScriptManager;

/**
 * Realisation of CommandBuilder.
 * Provide executing script.
 *
 * @since 1.0
 * @author safarislava
 */
public class ExecuteScriptCommand implements Command {
    private final String fileName;
    private final Client client;

    /**
     * Standard constructor.
     *
     * @param fileName Value of file name
     * @param client Value of client
     */
    public ExecuteScriptCommand(String fileName, Client client) {
        this.fileName = fileName;
        this.client = client;
    }

    @Override
    public String execute() {
        client.addCall(fileName);

        ScriptManager scriptManager = new ScriptManager(fileName);

        Input previousInput = client.getInput();

        client.setInput(scriptManager);
        client.start();
        client.setInput(previousInput);

        client.removeCall(fileName);

        return "Executed!\n";
    }
}
