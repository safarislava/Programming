package commands.builders;

import commands.Command;
import commands.ShowCommand;
import collection.OrganizationDAO;
import common.Client;

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
     * @param data    Value of data access object
     * @param client Value of program
     */
    public ShowCommandBuilder(OrganizationDAO data, Client client) {
        showCommand = new ShowCommand(data, client);
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
