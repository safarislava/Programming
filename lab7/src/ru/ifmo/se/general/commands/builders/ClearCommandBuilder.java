package ru.ifmo.se.general.commands.builders;

import ru.ifmo.se.general.interfaces.Input;
import ru.ifmo.se.general.commands.ClearCommand;
import ru.ifmo.se.general.commands.Command;
import ru.ifmo.se.general.interfaces.OrganizationData;
import ru.ifmo.se.general.commands.builders.interfaces.OrganizationDataCommandBuilder;

/**
 * Realisation of CommandBuilder.
 * Provide building clear command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ClearCommandBuilder implements OrganizationDataCommandBuilder {
    private OrganizationData data;

    @Override
    public Command build() {
        return new ClearCommand(data);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
    }

    @Override
    public String description() {
        return "Clear general.collection";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
