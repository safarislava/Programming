package Commands.Builders;

import Commands.Command;
import Commands.CountLessTypeCommand;
import Data.DAO;
import Entities.OrganizationType;

/**
 * Realisation of CommandBuilder.
 * Provide building count less type command.
 *
 * @since 1.0
 * @author safarislava
 */
public class CountLessTypeCommandBuilder implements CommandBuilder {
    private final DAO data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public CountLessTypeCommandBuilder(DAO data) {
        this.data = data;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        OrganizationType type = null;
        for (OrganizationType organizationType : OrganizationType.values()) {
            if (organizationType.name().equals(args[0])) type = organizationType;
        }
        if (type == null) throw new IllegalArgumentException("Invalid organization type");

        return new CountLessTypeCommand(type, data);
    }

    @Override
    public String description() {
        return "Print count of organizations which has less types";
    }
}
