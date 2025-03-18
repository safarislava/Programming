package ru.ifmo.se.general.commands.builders;

import ru.ifmo.se.general.commands.Command;
import ru.ifmo.se.general.commands.RemoveLowerCommand;
import ru.ifmo.se.general.interfaces.Input;
import ru.ifmo.se.general.interfaces.OrganizationData;
import ru.ifmo.se.general.commands.builders.interfaces.OrganizationDataCommandBuilder;
import ru.ifmo.se.general.entities.Organization;

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
    public void setArguments(String[] args, Input input) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");

        organization = input.getOrganization();
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
