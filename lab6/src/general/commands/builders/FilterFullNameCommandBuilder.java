package general.commands.builders;

import client.common.Input;
import general.commands.Command;
import general.commands.FilterFullNameCommand;
import general.collection.OrganizationData;

public class FilterFullNameCommandBuilder implements DataCommandBuilder {
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
    public void setData(OrganizationData data) {
        this.data = data;
    }
}
