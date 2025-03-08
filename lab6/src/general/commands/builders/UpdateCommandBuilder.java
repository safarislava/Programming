package general.commands.builders;

import general.commands.Command;
import general.commands.UpdateCommand;
import client.common.Input;
import general.collection.OrganizationData;
import general.commands.builders.interfaces.DataCommandBuilder;
import general.entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide building update command.
 *
 * @since 1.0
 * @author safarislava
 */
public class UpdateCommandBuilder implements DataCommandBuilder {
    private OrganizationData data;
    private int id;
    private Organization organization;

    @Override
    public Command build() {
        return new UpdateCommand(data, id, organization);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        id = Integer.parseInt(args[0]);
        organization = input.getOrganization();
    }

    @Override
    public String description() {
        return "Update fields of organization";
    }

    @Override
    public void setData(OrganizationData data) {
        this.data = data;
    }
}
