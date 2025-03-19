package ru.ifmo.se.general.command.builder;

import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.command.ClearCommand;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.command.builder.type.OrganizationDataCommandBuilder;

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
    public void setArguments(String[] args, Parser parser) {
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
