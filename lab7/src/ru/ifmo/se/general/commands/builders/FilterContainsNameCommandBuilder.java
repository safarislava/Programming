package ru.ifmo.se.general.commands.builders;

import ru.ifmo.se.general.interfaces.Input;
import ru.ifmo.se.general.commands.Command;
import ru.ifmo.se.general.commands.FilterContainsNameCommand;
import ru.ifmo.se.general.interfaces.OrganizationData;
import ru.ifmo.se.general.commands.builders.interfaces.OrganizationDataCommandBuilder;

public class FilterContainsNameCommandBuilder implements OrganizationDataCommandBuilder {
    private OrganizationData data;
    private String name;

    @Override
    public Command build() {
        return new FilterContainsNameCommand(name, data);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");
        name = args[0];
    }

    @Override
    public String description() {
        return "Print organizations which have same full name";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
