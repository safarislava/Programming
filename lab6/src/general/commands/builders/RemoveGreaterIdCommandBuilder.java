package general.commands.builders;

import client.common.Input;
import general.commands.Command;
import general.commands.RemoveGreaterIdCommand;
import general.collection.OrganizationData;

/**
 * Realisation of CommandBuilder.
 * Provide building remove greater key command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveGreaterIdCommandBuilder implements DataCommandBuilder {
    private OrganizationData data;
    private int id;

    @Override
    public Command build() {
        return new RemoveGreaterIdCommand(id, data);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        id = Integer.parseInt(args[0]);
    }

    @Override
    public String description() {
        return "Remove all organizations, which have a greater id";
    }

    @Override
    public void setData(OrganizationData data) {
        this.data = data;
    }
}
