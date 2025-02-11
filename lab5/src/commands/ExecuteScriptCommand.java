package commands;

import common.Program;
import common.ScriptManager;
import data.OrganizationDAO;

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
        ScriptManager scriptManager = new ScriptManager(fileName);

        Program program = new Program(data, scriptManager);
        program.start();
    }
}
