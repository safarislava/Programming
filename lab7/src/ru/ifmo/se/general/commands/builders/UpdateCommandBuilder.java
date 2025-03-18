package ru.ifmo.se.general.commands.builders;

import ru.ifmo.se.general.commands.Command;
import ru.ifmo.se.general.commands.UpdateCommand;
import ru.ifmo.se.general.interfaces.Input;
import ru.ifmo.se.general.interfaces.OrganizationData;
import ru.ifmo.se.general.commands.builders.interfaces.OrganizationDataCommandBuilder;
import ru.ifmo.se.general.entities.Organization;

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
    public void setArguments(String[] args, Input input) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        id = Integer.parseInt(args[0]);
        organization = input.getOrganization();
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
