package general.commands.builders;

import general.commands.Command;
import general.commands.CountLessTypeCommand;
import general.collection.OrganizationData;
import client.common.Input;
import general.entities.OrganizationType;

/**
 * Realisation of CommandBuilder.
 * Provide building count less type command.
 *
 * @since 1.0
 * @author safarislava
 */
public class CountLessTypeCommandBuilder implements DataCommandBuilder {
    private OrganizationData data;
    private OrganizationType type;

    /**
     * Standard constructor.
     *
     * @param data    Value of data access object
     * @param client
     */

    @Override
    public Command build() {
        return new CountLessTypeCommand(type, data);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
        type = input.getOrganizationType();
    }

    @Override
    public String description() {
        return "Print count of organizations which has less types";
    }

    @Override
    public void setData(OrganizationData data) {
        this.data = data;
    }
}
