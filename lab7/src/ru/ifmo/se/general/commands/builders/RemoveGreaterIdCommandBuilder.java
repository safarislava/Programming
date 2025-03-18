package ru.ifmo.se.general.commands.builders;

import ru.ifmo.se.general.interfaces.Input;
import ru.ifmo.se.general.commands.Command;
import ru.ifmo.se.general.commands.RemoveGreaterIdCommand;
import ru.ifmo.se.general.interfaces.OrganizationData;
import ru.ifmo.se.general.commands.builders.interfaces.OrganizationDataCommandBuilder;

/**
 * Realisation of CommandBuilder.
 * Provide building remove greater key command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveGreaterIdCommandBuilder implements OrganizationDataCommandBuilder {
    private OrganizationData data;
    private int id;

    @Override
    public Command build() {
        return new RemoveGreaterIdCommand(id, data);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        id = Integer.parseInt(args[0]);
    }

    @Override
    public String description() {
        return "Remove all organizations, which have a greater id";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
