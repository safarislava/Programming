package Commands.Builders;

import Commands.Command;
import Commands.RemoveGreaterCommand;
import Common.Input;
import Data.DAO;
import Entities.Address;
import Entities.Coordinates;
import Entities.Organization;
import Entities.OrganizationType;

/**
 * Realisation of CommandBuilder.
 * Provide building remove greater command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveGreaterCommandBuilder implements CommandBuilder {
    private final Input input;
    private final DAO data;

    /**
     * Standard constructor.
     *
     * @param input Value of input
     * @param data Value of data access object
     */
    public RemoveGreaterCommandBuilder(Input input, DAO data) {
        this.input = input;
        this.data = data;
    }

    @Override
    public Command build(String[] args) {
        if (args.length != 4) throw new IllegalArgumentException("Invalid number of arguments");

        long annualTurnover = Long.parseLong(args[1]);
        int employeesCount = Integer.parseInt(args[3]);
        Coordinates coordinates = input.getCoordinates();
        OrganizationType type = input.getOrganizationType();
        Address address = input.getAddress();

        Organization organization = new Organization(args[0], coordinates, annualTurnover,
                args[2], employeesCount, type, address);

        return new RemoveGreaterCommand(organization, data);
    }

    @Override
    public String description() {
        return "Remove all greater organizations";
    }
}
