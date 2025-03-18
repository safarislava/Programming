package ru.ifmo.se.general.commands.builders;

import ru.ifmo.se.general.commands.Command;
import ru.ifmo.se.general.commands.InsertCommand;
import ru.ifmo.se.general.interfaces.Input;
import ru.ifmo.se.general.interfaces.OrganizationData;
import ru.ifmo.se.general.commands.builders.interfaces.OrganizationDataCommandBuilder;
import ru.ifmo.se.general.commands.builders.interfaces.CreatorSetterCommandBuilder;
import ru.ifmo.se.general.entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide building insert command.
 *
 * @since 1.0
 * @author safarislava
 */
public class InsertCommandBuilder implements OrganizationDataCommandBuilder, CreatorSetterCommandBuilder {
    private OrganizationData data;
    private Organization organization;
    private String creator;

    @Override
    public Command build() {
        return new InsertCommand(data, organization, creator);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
        organization = input.getOrganization();
    }

    @Override
    public String description() {
        return "Add new organization";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }
}
