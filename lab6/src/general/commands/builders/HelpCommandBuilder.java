package general.commands.builders;

import client.common.Input;
import general.commands.Command;
import client.common.CommandController;
import general.commands.HelpCommand;
import general.commands.builders.interfaces.CommandBuilder;

/**
 * Realisation of CommandBuilder.
 * Provide building help command.
 *
 * @since 1.0
 * @author safarislava
 */
public class HelpCommandBuilder implements CommandBuilder {
    private final CommandController controller;

    /**
     * Standard constructor.
     *
     * @param controller Value of recognizing command controller
     */
    public HelpCommandBuilder(CommandController controller) {
        this.controller = controller;
    }

    @Override
    public Command build() {
        return new HelpCommand(controller);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
    }

    @Override
    public String description() {
        return "Print all commands";
    }
}
