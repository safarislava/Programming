package commands.builders;

import commands.Command;
import commands.ShowCommand;
import data.OrganizationDAO;

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
    public ShowCommandBuilder(OrganizationDAO data) {
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
