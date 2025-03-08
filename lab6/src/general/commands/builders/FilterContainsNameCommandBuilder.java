package general.commands.builders;

import client.common.Input;
import general.commands.Command;
import general.commands.FilterContainsNameCommand;
import general.collection.OrganizationData;
import general.commands.builders.interfaces.DataCommandBuilder;

public class FilterContainsNameCommandBuilder implements DataCommandBuilder {
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
    public void setData(OrganizationData data) {
        this.data = data;
    }
}
