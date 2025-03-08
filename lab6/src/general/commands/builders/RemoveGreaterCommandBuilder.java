package general.commands.builders;

import general.commands.Command;
import general.commands.RemoveGreaterCommand;
import client.common.Input;
import general.collection.OrganizationData;
import general.entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide building remove greater command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveGreaterCommandBuilder implements DataCommandBuilder {
    private OrganizationData data;
    private Organization organization;

    @Override
    public Command build() {
        return new RemoveGreaterCommand(organization, data);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");

        organization = input.getOrganization();
    }

    @Override
    public String description() {
        return "Remove all greater organizations";
    }

    @Override
    public void setData(OrganizationData data) {
        this.data = data;
    }
}
