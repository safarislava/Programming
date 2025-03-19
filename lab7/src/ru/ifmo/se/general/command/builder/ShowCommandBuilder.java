package ru.ifmo.se.general.command.builder;

import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.ShowCommand;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.command.builder.type.OrganizationDataCommandBuilder;

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
    public void setArguments(String[] args, Parser parser) {
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
