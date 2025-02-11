package Commands.Builders;

import Commands.Command;
import Commands.UpdateCommand;
import Common.Input;
import Data.DAO;
import Entities.Address;
import Entities.Coordinates;
import Entities.Organization;
import Entities.OrganizationType;

/**
 * Realisation of CommandBuilder.
 * Provide building update command.
 *
 * @since 1.0
 * @author safarislava
 */
public class UpdateCommandBuilder implements CommandBuilder {
    private final DAO data;
    private final Input input;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     * @param input Value of class asking missing info
     */
    public UpdateCommandBuilder(DAO data, Input input) {
        this.data = data;
        this.input = input;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 5) throw new IllegalArgumentException("Invalid number of arguments");

        int id = Integer.parseInt(args[0]);
        long annualTurnover = Long.parseLong(args[2]);
        int employeesCount = Integer.parseInt(args[4]);
        Coordinates coordinates = input.getCoordinates();
        OrganizationType type = input.getOrganizationType();
        Address address = input.getAddress();

        Organization organization = new Organization(args[1], coordinates, annualTurnover,
                args[3], employeesCount, type, address);

        return new UpdateCommand(data, id, organization);
    }

    @Override
    public String description() {
        return "Update fields of organization";
    }
}
