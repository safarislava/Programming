package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.UpdateCommand;
import ru.ifmo.se.general.command.assembler.type.OrganizationDataRequired;
import ru.ifmo.se.general.contract.OrganizationConverter;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.entity.Organization;
import ru.ifmo.se.general.common.Parser;

/**
 * Realisation of CommandAssembler.
 * Provide assembling update command.
 *
 * @since 1.0
 * @author safarislava
 */
public class UpdateCommandAssembler implements OrganizationDataRequired {
    private OrganizationData data;
    private int id;
    private Organization organization;

    @Override
    public Command assemble() {
        return new UpdateCommand(data, id, organization);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length == 1) {
            organization = OrganizationConverter.decode(args[0]).get(0);
            id = organization.getId();
        }
        else if (args.length == 0) {
            organization = parser.getOrganizationWithId();
            if (organization == null) throw new NullPointerException("Organization not found");
            id = organization.getId();
        }
        else throw new IllegalArgumentException("Invalid number of arguments");
    }

    @Override
    public String description() {
        return "Update fields of organization";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
