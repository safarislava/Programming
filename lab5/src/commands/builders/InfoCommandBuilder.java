package commands.builders;

import commands.Command;
import commands.InfoCommand;
import collection.OrganizationDAO;
import common.Client;

/**
 * Realisation of CommandBuilder.
 * Provide building info command.
 *
 * @since 1.0
 * @author safarislava
 */
public class InfoCommandBuilder implements CommandBuilder {
    private final InfoCommand infoCommand;

    /**
     * Standard constructor.
     *
     * @param data    Value of data access object
     * @param client Value of program
     */
    public InfoCommandBuilder(OrganizationDAO data, Client client) {
        this.infoCommand = new InfoCommand(data, client);
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
        return infoCommand;
    }

    @Override
    public String description() {
        return "Displays information type of collection and count of elements";
    }
}
