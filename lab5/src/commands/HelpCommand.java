package commands;

import commands.builders.CommandBuilder;
import common.Client;

import java.util.HashMap;

/**
 * Realisation of CommandBuilder.
 * Provide printing all accessed commands.
 *
 * @since 1.0
 * @author safarislava
 */
public class HelpCommand implements Command {
    private final CommandController controller;
    private final Client client;

    /**
     * Standard constructor.
     *
     * @param controller Value of recognizing command controller
     */
    public HelpCommand(CommandController controller, Client client) {
        this.controller = controller;
        this.client = client;
    }

    @Override
    public void execute() {
        HashMap<String, CommandBuilder> commandBuilders =  controller.getCommandBuilders();

        StringBuilder text = new StringBuilder();

        commandBuilders.keySet().forEach(key ->
                text.append(String.format("%-20s%s%n", key, commandBuilders.get(key).description())));

        client.showText(text.toString());
    }
}
