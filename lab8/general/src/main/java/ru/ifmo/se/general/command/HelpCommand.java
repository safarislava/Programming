package ru.ifmo.se.general.command;

import ru.ifmo.se.general.command.assembler.CommandAssembler;
import ru.ifmo.se.general.common.AbstractCommandManager;

import java.util.Map;

/**
 * Realisation of CommandAssembler.
 * Provide printing all accessed ru.ifmo.se.general.commands.
 *
 * @since 1.0
 * @author safarislava
 */
public class HelpCommand implements Command {
    private final AbstractCommandManager controller;

    /**
     * Standard constructor.
     *
     * @param controller Value of recognizing command controller
     */
    public HelpCommand(AbstractCommandManager controller) {
        this.controller = controller;
    }

    @Override
    public String execute() {
        Map<String, CommandAssembler> commandAssemblers = controller.getCommandAssemblers();

        StringBuilder text = new StringBuilder();

        commandAssemblers.keySet().forEach(key ->
                text.append(String.format("%-20s%s%n", key, commandAssemblers.get(key).description())));

        return text.toString();
    }
}
