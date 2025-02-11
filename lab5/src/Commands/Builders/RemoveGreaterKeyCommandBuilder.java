package Commands.Builders;

import Commands.Command;
import Commands.RemoveGreaterKeyCommand;
import Data.DAO;

/**
 * Realisation of CommandBuilder.
 * Provide building remove greater key command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveGreaterKeyCommandBuilder implements CommandBuilder {
    private final DAO data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public RemoveGreaterKeyCommandBuilder(DAO data) {
        this.data = data;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        return new RemoveGreaterKeyCommand(args[0], data);
    }

    @Override
    public String description() {
        return "Remove all organizations, which have a greater key";
    }
}
