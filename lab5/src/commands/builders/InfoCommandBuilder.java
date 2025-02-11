package commands.builders;

import commands.Command;
import commands.InfoCommand;
import data.OrganizationDAO;

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
     * @param data Value of data access object
     */
    public InfoCommandBuilder(OrganizationDAO data) {
        this.infoCommand = new InfoCommand(data);
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
