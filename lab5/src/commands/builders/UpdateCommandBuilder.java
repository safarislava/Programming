package commands.builders;

import commands.Command;
import commands.UpdateCommand;
import common.Input;
import collection.OrganizationDAO;
import entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide building update command.
 *
 * @since 1.0
 * @author safarislava
 */
public class UpdateCommandBuilder implements CommandBuilder {
    private final OrganizationDAO data;
    private final Input input;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     * @param input Value of class asking missing info
     */
    public UpdateCommandBuilder(OrganizationDAO data, Input input) {
        this.data = data;
        this.input = input;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        int id = Integer.parseInt(args[0]);
        Organization organization = input.getOrganization();

        return new UpdateCommand(data, id, organization);
    }

    @Override
    public String description() {
        return "Update fields of organization";
    }
}
