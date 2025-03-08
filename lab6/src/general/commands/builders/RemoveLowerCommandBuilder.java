package general.commands.builders;

import general.commands.Command;
import general.commands.RemoveLowerCommand;
import client.common.Input;
import general.collection.OrganizationData;
import general.commands.builders.interfaces.DataCommandBuilder;
import general.entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide building remove lower command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveLowerCommandBuilder implements DataCommandBuilder {
    private OrganizationData data;
    private Organization organization;

    @Override
    public Command build() {
        return new RemoveLowerCommand(organization, data);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");

        organization = input.getOrganization();
    }

    @Override
    public String description() {
        return "Remove all lower organizations";
    }

    @Override
    public void setData(OrganizationData data) {
        this.data = data;
    }
}
