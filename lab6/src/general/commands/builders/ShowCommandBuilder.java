package general.commands.builders;

import client.common.Input;
import general.commands.Command;
import general.commands.ShowCommand;
import general.collection.OrganizationData;
import general.commands.builders.interfaces.DataCommandBuilder;

/**
 * Realisation of CommandBuilder.
 * Provide building show command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ShowCommandBuilder implements DataCommandBuilder {
    private OrganizationData data;

    @Override
    public Command build() {
        return new ShowCommand(data);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
    }

    @Override
    public String description() {
        return "Show all elements in the general.collection";
    }

    @Override
    public void setData(OrganizationData data) {
        this.data = data;
    }
}
