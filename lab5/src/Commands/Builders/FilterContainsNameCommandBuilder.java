package Commands.Builders;

import Commands.Command;
import Commands.FilterContainsNameCommand;
import Data.DAO;

public class FilterContainsNameCommandBuilder implements CommandBuilder {
    private final DAO data;

    public FilterContainsNameCommandBuilder(DAO data) {
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
