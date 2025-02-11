package Commands.Builders;

import Commands.Command;
import Commands.RemoveCommand;
import Data.DAO;

/**
 * Realisation of CommandBuilder.
 * Provide building remove command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveCommandBuilder implements CommandBuilder {
    private final DAO data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public RemoveCommandBuilder(DAO data) {
        this.data = data;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");
        return new RemoveCommand(data, args[0]);
    }

    @Override
    public String description() {
        return "Remove element by key";
    }
}
