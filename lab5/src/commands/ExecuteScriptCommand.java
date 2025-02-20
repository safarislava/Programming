package commands;

import common.Program;
import common.ScriptManager;
import data.OrganizationDAO;

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
    private final Set<String> calls;

    /**
     * Standard constructor.
     *
     * @param fileName Value of file name
     * @param data Value of data access object
     */
    public ExecuteScriptCommand(String fileName, OrganizationDAO data, Set<String> calls) {
        this.fileName = fileName;
        this.data = data;
        this.calls = calls;
    }

    @Override
    public void execute() {
        if (calls.contains(fileName)) throw new RuntimeException("Recursive call");
        calls.add(fileName);

        ScriptManager scriptManager = new ScriptManager(fileName);

        Program program = new Program(data, scriptManager, calls);

        program.start();
        calls.remove(fileName);
    }
}
