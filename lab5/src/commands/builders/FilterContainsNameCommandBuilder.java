package commands.builders;

import commands.Command;
import commands.FilterContainsNameCommand;
import collection.OrganizationDAO;
import common.Client;

public class FilterContainsNameCommandBuilder implements CommandBuilder {
    private final OrganizationDAO data;
    private final Client client;

    public FilterContainsNameCommandBuilder(OrganizationDAO data, Client client) {
        this.data = data;
        this.client = client;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        return new FilterContainsNameCommand(args[0], data, client);
    }

    @Override
    public String description() {
        return "Print organizations which have same full name";
    }
}
