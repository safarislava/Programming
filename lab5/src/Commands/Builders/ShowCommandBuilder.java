package Commands.Builders;

import Commands.Command;
import Commands.ShowCommand;
import Data.DAO;

/**
 * Realisation of CommandBuilder.
 * Provide building show command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ShowCommandBuilder implements CommandBuilder {
    private final ShowCommand showCommand;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public ShowCommandBuilder(DAO data) {
        showCommand = new ShowCommand(data);
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
        return showCommand;
    }

    @Override
    public String description() {
        return "Show all elements in the collection";
    }
}
