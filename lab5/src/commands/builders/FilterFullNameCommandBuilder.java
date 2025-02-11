package commands.builders;

import commands.Command;
import commands.FilterFullNameCommand;
import data.OrganizationDAO;

public class FilterFullNameCommandBuilder implements CommandBuilder {
    private final OrganizationDAO data;

    public FilterFullNameCommandBuilder(OrganizationDAO data) {
        this.data = data;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        return new FilterFullNameCommand(args[0], data);
    }

    @Override
    public String description() {
        return "Print organizations which contains string in name";
    }
}
