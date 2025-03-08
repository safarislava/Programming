package commands.builders;

import commands.Command;
import commands.SaveCommand;
import collection.OrganizationDAO;

/**
 * Realisation of CommandBuilder.
 * Provide building save command.
 *
 * @since 1.0
 * @author safarislava
 */
public class SaveCommandBuilder implements CommandBuilder {
    private final OrganizationDAO data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public SaveCommandBuilder(OrganizationDAO data) {
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
