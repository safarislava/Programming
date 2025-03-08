package general.commands;

import client.Client;
import client.common.Input;
import client.common.ScriptManager;

import java.util.Set;

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
     * @param data Value of data access object
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
