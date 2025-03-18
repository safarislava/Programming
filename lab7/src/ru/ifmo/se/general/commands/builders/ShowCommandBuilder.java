package ru.ifmo.se.general.commands.builders;

import ru.ifmo.se.general.interfaces.Input;
import ru.ifmo.se.general.commands.Command;
import ru.ifmo.se.general.commands.ShowCommand;
import ru.ifmo.se.general.interfaces.OrganizationData;
import ru.ifmo.se.general.commands.builders.interfaces.OrganizationDataCommandBuilder;

/**
 * Realisation of CommandBuilder.
 * Provide building show command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ShowCommandBuilder implements OrganizationDataCommandBuilder {
    private OrganizationData data;

    @Override
    public Command build() {
        return new ShowCommand(data);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
    }

    @Override
    public String description() {
        return "Show all elements in the general.collection";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
