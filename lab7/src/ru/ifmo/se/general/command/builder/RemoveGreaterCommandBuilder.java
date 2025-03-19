package ru.ifmo.se.general.command.builder;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.RemoveGreaterCommand;
import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.command.builder.type.OrganizationDataCommandBuilder;
import ru.ifmo.se.general.entity.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide building remove greater command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveGreaterCommandBuilder implements OrganizationDataCommandBuilder {
    private OrganizationData data;
    private Organization organization;

    @Override
    public Command build() {
        return new RemoveGreaterCommand(organization, data);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");

        organization = parser.getOrganization();
    }

    @Override
    public String description() {
        return "Remove all greater organizations";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
