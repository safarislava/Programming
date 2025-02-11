package Commands.Builders;

import Commands.Command;
import Commands.SaveCommand;
import Data.DAO;

/**
 * Realisation of CommandBuilder.
 * Provide building save command.
 *
 * @since 1.0
 * @author safarislava
 */
public class SaveCommandBuilder implements CommandBuilder {
    private final DAO data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public SaveCommandBuilder(DAO data) {
        this.data = data;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        return new SaveCommand(data, args[0]);
    }

    @Override
    public String description() {
        return "Save collection into file";
    }
}
