package ru.ifmo.se.general.command;

import ru.ifmo.se.general.common.AbstractClient;
import ru.ifmo.se.general.contract.CodePhrase;
import ru.ifmo.se.general.parser.Parser;

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
    private final AbstractClient client;

    /**
     * Standard constructor.
     *
     * @param file Path of script
     * @param client Value of client
     */
    public ExecuteScriptCommand(Path file, AbstractClient client) {
        this.file = file;
        this.client = client;
    }

    @Override
    public String execute() {
        client.addCall(file.getFileName().toString());

        Parser previousParser = client.getInput();
        Parser newParser = client.getInput(file);
        client.setInput(newParser);

        while (newParser.hasNext()) {
            String[] commandArray = newParser.getCommandArray();
            String command = commandArray[0];
            String[] args = Arrays.copyOfRange(commandArray, 1, commandArray.length);

            client.execute(command, args);
        }

        client.setInput(previousParser);
        client.removeCall(file.getFileName().toString());

        return CodePhrase.SUCCESSFUL;
    }
}
