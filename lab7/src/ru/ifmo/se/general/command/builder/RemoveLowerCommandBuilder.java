package ru.ifmo.se.general.command.builder;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.RemoveLowerCommand;
import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.command.builder.type.OrganizationDataCommandBuilder;
import ru.ifmo.se.general.entity.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide building remove lower command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveLowerCommandBuilder implements OrganizationDataCommandBuilder {
    private OrganizationData data;
    private Organization organization;

    @Override
    public Command build() {
        return new RemoveLowerCommand(organization, data);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");

        organization = parser.getOrganization();
    }

    @Override
    public String description() {
        return "Remove all lower organizations";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
