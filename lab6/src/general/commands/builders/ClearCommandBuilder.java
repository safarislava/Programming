package general.commands.builders;

import client.common.Input;
import general.commands.ClearCommand;
import general.commands.Command;
import general.collection.OrganizationData;
import general.commands.builders.interfaces.DataCommandBuilder;

/**
 * Realisation of CommandBuilder.
 * Provide building clear command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ClearCommandBuilder implements DataCommandBuilder {
    private OrganizationData data;

    @Override
    public Command build() {
        return new ClearCommand(data);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
    }

    @Override
    public String description() {
        return "Clear general.collection";
    }

    @Override
    public void setData(OrganizationData data) {
        this.data = data;
    }
}
