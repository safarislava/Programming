package ru.ifmo.se.general.commands.builders;

import ru.ifmo.se.general.interfaces.Input;
import ru.ifmo.se.general.commands.Command;
import ru.ifmo.se.general.commands.FilterFullNameCommand;
import ru.ifmo.se.general.interfaces.OrganizationData;
import ru.ifmo.se.general.commands.builders.interfaces.OrganizationDataCommandBuilder;

public class FilterFullNameCommandBuilder implements OrganizationDataCommandBuilder {
    private OrganizationData data;
    private String fullName;

    @Override
    public Command build() {
        return new FilterFullNameCommand(fullName, data);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");
        fullName = args[0];
    }

    @Override
    public String description() {
        return "Print organizations which contains string in name";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
