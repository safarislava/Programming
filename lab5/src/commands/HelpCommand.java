package commands;

import commands.builders.CommandBuilder;

import java.util.HashMap;

/**
 * Realisation of CommandBuilder.
 * Provide printing all accessed commands.
 *
 * @since 1.0
 * @author safarislava
 */
public class HelpCommand implements Command {
    CommandController controller;

    /**
     * Standard constructor.
     *
     * @param controller Value of recognizing command controller
     */
    public HelpCommand(CommandController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        HashMap<String, CommandBuilder> commandBuilders =  controller.getCommandBuilders();

        commandBuilders.keySet().forEach(key ->
                System.out.printf("%-20s%s%n", key, commandBuilders.get(key).description()));
    }
}
