package commands.builders;

import commands.Command;
import commands.CommandController;
import commands.HelpCommand;
import common.Client;

/**
 * Realisation of CommandBuilder.
 * Provide building help command.
 *
 * @since 1.0
 * @author safarislava
 */
public class HelpCommandBuilder implements CommandBuilder {
    private final HelpCommand helpCommand;

    /**
     * Standard constructor.
     *
     * @param controller Value of recognizing command controller
     * @param client
     */
    public HelpCommandBuilder(CommandController controller, Client client) {
        helpCommand = new HelpCommand(controller, client);
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
        return helpCommand;
    }

    @Override
    public String description() {
        return "Print all commands";
    }
}
