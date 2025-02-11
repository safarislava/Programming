package commands.builders;

import commands.Command;
import commands.InsertCommand;
import common.Input;
import data.OrganizationDAO;
import entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide building insert command.
 *
 * @since 1.0
 * @author safarislava
 */
public class InsertCommandBuilder implements CommandBuilder {
    private final OrganizationDAO data;
    private final Input input;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     * @param input Value of class asking missing info
     */
    public InsertCommandBuilder(OrganizationDAO data, Input input) {
        this.data = data;
        this.input = input;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");

        Organization organization = input.getOrganization();

        return new InsertCommand(data, organization);

    }

    @Override
    public String description() {
        return "Add new organization";
    }
}
