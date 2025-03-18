package ru.ifmo.se.general.commands.builders;

import ru.ifmo.se.general.commands.Command;
import ru.ifmo.se.general.commands.CountLessTypeCommand;
import ru.ifmo.se.general.interfaces.OrganizationData;
import ru.ifmo.se.general.interfaces.Input;
import ru.ifmo.se.general.commands.builders.interfaces.OrganizationDataCommandBuilder;
import ru.ifmo.se.general.entities.OrganizationType;

/**
 * Realisation of CommandBuilder.
 * Provide building count less type command.
 *
 * @since 1.0
 * @author safarislava
 */
public class CountLessTypeCommandBuilder implements OrganizationDataCommandBuilder {
    private OrganizationData data;
    private OrganizationType type;

    @Override
    public Command build() {
        return new CountLessTypeCommand(type, data);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
        type = input.getOrganizationType();
    }

    @Override
    public String description() {
        return "Print count of organizations which has less types";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
