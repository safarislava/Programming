package ru.ifmo.se.general.command.builder;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.UpdateCommand;
import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.command.builder.type.OrganizationDataCommandBuilder;
import ru.ifmo.se.general.entity.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide building update command.
 *
 * @since 1.0
 * @author safarislava
 */
public class UpdateCommandBuilder implements OrganizationDataCommandBuilder {
    private OrganizationData data;
    private int id;
    private Organization organization;

    @Override
    public Command build() {
        return new UpdateCommand(data, id, organization);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        id = Integer.parseInt(args[0]);
        organization = parser.getOrganization();
    }

    @Override
    public String description() {
        return "Update fields of organization";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
