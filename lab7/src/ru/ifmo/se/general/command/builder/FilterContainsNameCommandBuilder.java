package ru.ifmo.se.general.command.builder;

import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.FilterContainsNameCommand;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.command.builder.type.OrganizationDataCommandBuilder;

public class FilterContainsNameCommandBuilder implements OrganizationDataCommandBuilder {
    private OrganizationData data;
    private String name;

    @Override
    public Command build() {
        return new FilterContainsNameCommand(name, data);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
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
