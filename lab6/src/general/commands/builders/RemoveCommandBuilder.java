package general.commands.builders;

import client.common.Input;
import general.commands.Command;
import general.commands.RemoveCommand;
import general.collection.OrganizationData;
import general.commands.builders.interfaces.DataCommandBuilder;

/**
 * Realisation of CommandBuilder.
 * Provide building remove command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveCommandBuilder implements DataCommandBuilder {
    private OrganizationData data;
    private int id;

    @Override
    public Command build() {

        return new RemoveCommand(data, id);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");
        id = Integer.parseInt(args[0]);
    }

    @Override
    public String description() {
        return "Remove element by id";
    }

    @Override
    public void setData(OrganizationData data) {
        this.data = data;
    }
}
