package commands.builders;

import commands.Command;
import commands.FilterFullNameCommand;
import collection.OrganizationDAO;
import common.Client;

public class FilterFullNameCommandBuilder implements CommandBuilder {
    private final OrganizationDAO data;
    private final Client client;

    public FilterFullNameCommandBuilder(OrganizationDAO data, Client client) {
        this.data = data;
        this.client = client;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        return new FilterFullNameCommand(args[0], data, client);
    }

    @Override
    public String description() {
        return "Print organizations which contains string in name";
    }
}
