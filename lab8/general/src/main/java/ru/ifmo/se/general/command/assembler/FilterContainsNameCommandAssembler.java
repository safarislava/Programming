package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.FilterContainsNameCommand;
import ru.ifmo.se.general.command.assembler.type.OrganizationDataRequired;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.parser.Parser;

public class FilterContainsNameCommandAssembler implements OrganizationDataRequired {
    private OrganizationData data;
    private String name;

    @Override
    public Command assemble() {
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
