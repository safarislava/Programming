package commands.builders;

import commands.Command;
import commands.RemoveGreaterCommand;
import common.Input;
import collection.OrganizationDAO;
import entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide building remove greater command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveGreaterCommandBuilder implements CommandBuilder {
    private final Input input;
    private final OrganizationDAO data;

    /**
     * Standard constructor.
     *
     * @param input Value of input
     * @param data Value of data access object
     */
    public RemoveGreaterCommandBuilder(Input input, OrganizationDAO data) {
        this.input = input;
        this.data = data;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");

        Organization organization = input.getOrganization();

        return new RemoveGreaterCommand(organization, data);
    }

    @Override
    public String description() {
        return "Remove all greater organizations";
    }
}
