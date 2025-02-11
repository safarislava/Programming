package commands.builders;

import commands.Command;
import commands.ExitCommand;
import common.Program;

/**
 * Realisation of CommandBuilder.
 * Provide building exit command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ExitCommandBuilder implements CommandBuilder {
    private final ExitCommand exitCommand;

    /**
     * Standard constructor.
     *
     * @param program Value of lifecycle class
     */
    public ExitCommandBuilder(Program program) {
        exitCommand = new ExitCommand(program);
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
        return exitCommand;
    }

    @Override
    public String description(){
        return "Exits the program";
    }
}
