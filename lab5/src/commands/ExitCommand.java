package commands;

import common.Program;

/**
 * Realisation of CommandBuilder.
 * Provide stopping lifecycle of program.
 *
 * @since 1.0
 * @author safarislava
 */
public class ExitCommand implements Command {
    private final Program program;

    /**
     * Standard constructor.
     *
     * @param program Value of lifecycle class
     */
    public ExitCommand(Program program) {
        this.program = program;
    }

    @Override
    public void execute() {
        program.stop();
    }
}
