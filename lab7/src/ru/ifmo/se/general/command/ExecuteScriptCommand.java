package ru.ifmo.se.general.command;

import ru.ifmo.se.client.Client;
import ru.ifmo.se.general.Parser;
import ru.ifmo.se.client.parser.ScriptParser;

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

        ScriptParser scriptParser = new ScriptParser(fileName);

        Parser previousParser = client.getInput();

        client.setInput(scriptParser);
        client.start();
        client.setInput(previousParser);

        client.removeCall(fileName);

        return "Executed!\n";
    }
}
