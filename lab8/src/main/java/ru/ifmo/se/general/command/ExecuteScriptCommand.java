package ru.ifmo.se.general.command;

import ru.ifmo.se.client.Client;
import ru.ifmo.se.general.contract.CodePhrase;
import ru.ifmo.se.general.parser.Parser;
import ru.ifmo.se.client.parser.ScriptParser;

import java.nio.file.Path;
import java.util.Arrays;

/**
 * Realisation of CommandAssembler.
 * Provide executing script.
 *
 * @since 1.0
 * @author safarislava
 */
public class ExecuteScriptCommand implements Command {
    private final Path file;
    private final Client client;

    /**
     * Standard constructor.
     *
     * @param file Path of script
     * @param client Value of client
     */
    public ExecuteScriptCommand(Path file, Client client) {
        this.file = file;
        this.client = client;
    }

    @Override
    public String execute() {
        client.addCall(file.getFileName().toString());

        ScriptParser scriptParser = new ScriptParser(file);
        Parser previousParser = client.getInput();
        client.setInput(scriptParser);

        while (scriptParser.hasNext()) {
            String[] commandArray = scriptParser.getCommandArray();
            String command = commandArray[0];
            String[] args = Arrays.copyOfRange(commandArray, 1, commandArray.length);

            client.execute(command, args);
        }

        client.setInput(previousParser);
        client.removeCall(file.getFileName().toString());

        return CodePhrase.SUCCESSFUL;
    }
}
