package ru.ifmo.se.general.command;

import ru.ifmo.se.general.common.ClientInterface;
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
    private final ClientInterface client;
    private final Parser parser;

    /**
     * Standard constructor.
     *
     * @param file Path of script
     * @param client Value of client
     */
    public ExecuteScriptCommand(Path file, ClientInterface client, Parser parser) {
        this.file = file;
        this.client = client;
        this.parser = parser;
    }

    @Override
    public String execute() {
        client.addCall(file.getFileName().toString());

        Parser previousParser = client.getInput();
        client.setInput(parser);

        while (parser.hasNext()) {
            String[] commandArray = parser.getCommandArray();
            String command = commandArray[0];
            String[] args = Arrays.copyOfRange(commandArray, 1, commandArray.length);

            client.execute(command, args);
        }

        client.setInput(previousParser);
        client.removeCall(file.getFileName().toString());

        return CodePhrase.SUCCESSFUL;
    }
}
