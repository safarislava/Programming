package general.commands;

import client.common.CommandController;
import general.commands.builders.interfaces.CommandBuilder;

import java.util.HashMap;

/**
 * Realisation of CommandBuilder.
 * Provide printing all accessed general.commands.
 *
 * @since 1.0
 * @author safarislava
 */
public class HelpCommand implements Command {
    private final CommandController controller;

    /**
     * Standard constructor.
     *
     * @param controller Value of recognizing command controller
     */
    public HelpCommand(CommandController controller) {
        this.controller = controller;
    }

    @Override
    public String execute() {
        HashMap<String, CommandBuilder> commandBuilders =  controller.getCommandBuilders();

        StringBuilder text = new StringBuilder();

        commandBuilders.keySet().forEach(key ->
                text.append(String.format("%-20s%s%n", key, commandBuilders.get(key).description())));

        return text.toString();
    }
}
