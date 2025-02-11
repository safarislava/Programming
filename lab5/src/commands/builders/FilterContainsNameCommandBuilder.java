package commands.builders;

import commands.Command;
import commands.FilterContainsNameCommand;
import data.OrganizationDAO;

public class FilterContainsNameCommandBuilder implements CommandBuilder {
    private final OrganizationDAO data;

    public FilterContainsNameCommandBuilder(OrganizationDAO data) {
        this.data = data;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        return new FilterContainsNameCommand(args[0], data);
    }

    @Override
    public String description() {
        return "Print organizations which have same full name";
    }
}
