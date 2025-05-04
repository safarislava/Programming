package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.FilterFullNameCommand;
import ru.ifmo.se.general.command.assembler.type.OrganizationDataRequired;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.common.Parser;

public class FilterFullNameCommandAssembler implements OrganizationDataRequired {
    private OrganizationData data;
    private String fullName;

    @Override
    public Command assemble() {
        return new FilterFullNameCommand(fullName, data);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
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
