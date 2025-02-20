package commands;

import common.Program;
import common.ScriptManager;
import data.OrganizationDAO;

import java.util.HashSet;
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
    private final OrganizationDAO data;

    private static final Set<String> calls = new HashSet<>();

    /**
     * Standard constructor.
     *
     * @param fileName Value of file name
     * @param data Value of data access object
     */
    public ExecuteScriptCommand(String fileName, OrganizationDAO data) {
        this.fileName = fileName;
        this.data = data;
    }

    @Override
    public void execute() {
        if (calls.contains(fileName)) throw new RuntimeException("Recursive call");

        ScriptManager scriptManager = new ScriptManager(fileName);

        Program program = new Program(data, scriptManager);
        calls.add(fileName);

        program.start();
        calls.remove(fileName);
    }
}
