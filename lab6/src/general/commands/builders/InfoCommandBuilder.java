package general.commands.builders;

import client.common.Input;
import general.commands.Command;
import general.commands.InfoCommand;
import general.collection.OrganizationData;

/**
 * Realisation of CommandBuilder.
 * Provide building info command.
 *
 * @since 1.0
 * @author safarislava
 */
public class InfoCommandBuilder implements DataCommandBuilder {
    private OrganizationData data;

    @Override
    public Command build() {
        return new InfoCommand(data);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
    }

    @Override
    public String description() {
        return "Displays information type of general.collection and count of elements";
    }

    @Override
    public void setData(OrganizationData data) {
        this.data = data;
    }
}
