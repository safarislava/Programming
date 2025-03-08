package general.commands.builders;

import general.commands.Command;
import general.commands.InsertCommand;
import client.common.Input;
import general.collection.OrganizationData;
import general.commands.builders.interfaces.DataCommandBuilder;
import general.commands.builders.interfaces.IdSetterCommandBuilder;
import general.entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide building insert command.
 *
 * @since 1.0
 * @author safarislava
 */
public class InsertCommandBuilder implements DataCommandBuilder, IdSetterCommandBuilder {
    private OrganizationData data;
    private Organization organization;

    @Override
    public Command build() {
        return new InsertCommand(data, organization);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
        organization = input.getOrganization();
    }

    @Override
    public String description() {
        return "Add new organization";
    }

    @Override
    public void setData(OrganizationData data) {
        this.data = data;
    }

    @Override
    public void setId(int id) {
        organization.setId(id);
    }
}
