package Commands.Builders;

import Commands.Command;
import Commands.FilterFullNameCommand;
import Data.DAO;

public class FilterFullNameCommandBuilder implements CommandBuilder {
    private final DAO data;

    public FilterFullNameCommandBuilder(DAO data) {
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
