package commands.builders;

import commands.Command;
import commands.ExitCommand;
import common.Client;

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
     * @param client Value of lifecycle class
     */
    public ExitCommandBuilder(Client client) {
        exitCommand = new ExitCommand(client);
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
